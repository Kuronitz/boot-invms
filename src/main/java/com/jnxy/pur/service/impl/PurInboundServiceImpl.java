package com.jnxy.pur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.common.vo.UserContext;
import com.jnxy.pur.entity.PurInbound;
import com.jnxy.pur.entity.PurInboundSub;
import com.jnxy.pur.entity.PurOrder;
import com.jnxy.pur.mapper.PurInboundMapper;
import com.jnxy.pur.mapper.PurInboundSubMapper;
import com.jnxy.pur.mapper.PurOrderMapper;
import com.jnxy.pur.service.IPurInboundService;
import com.jnxy.stock.entity.StockInfo;
import com.jnxy.stock.mapper.StockInfoMapper;
import com.jnxy.sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 采购入库单信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class PurInboundServiceImpl extends ServiceImpl<PurInboundMapper, PurInbound> implements IPurInboundService {
    @Resource
    private PurInboundSubMapper purInboundSubMapper;

    @Resource
    private StockInfoMapper stockInfoMapper;

    @Resource
    private PurOrderMapper purOrderMapper;

    public static String generateCode() {
        String date = new SimpleDateFormat("yyMMdd").format(new Date());
        StringBuilder dateStr = new StringBuilder(date);
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        int sum = random.nextInt(max) % (max - min + 1) + min;
        dateStr.append(sum);
        return dateStr.toString().toLowerCase();
    }

    @Override
    @Transactional
    public void addInbound(PurInbound purInbound) {
        LambdaQueryWrapper<PurOrder> purNumWrapper = new LambdaQueryWrapper<>();
        purNumWrapper.eq(PurOrder::getPurchaseNum, purInbound.getPurchaseNum());
        PurOrder purOrder = new PurOrder();
        purOrder.setPurchaseNum(purInbound.getPurchaseNum());
        purOrder.setStatus(1);
        Date date = new Date();
        purOrder.setInboundDate(date);
        String code = generateCode();
        purInbound.setInboundCode(code);
        User user = UserContext.getCurrentUserObj();
        purInbound.setStkId(user.getId());
        purInbound.setStkName(user.getUsername());
        purOrder.setStkId(user.getId());
        purOrder.setStkName(user.getUsername());
        purOrderMapper.update(purOrder, purNumWrapper);
        this.baseMapper.insert(purInbound);
        if (purInbound.getPurInboundSubList() != null) {
            for (PurInboundSub purInboundSub : purInbound.getPurInboundSubList()) {
                purInboundSub.setInboundCodeSub(code);
                purInboundSubMapper.insert(purInboundSub);
                LambdaQueryWrapper<StockInfo> goodsIdWrapper = new LambdaQueryWrapper<>();
                goodsIdWrapper.eq(StockInfo::getGoodsId, purInboundSub.getGoodsId());
                StockInfo stockInfo = stockInfoMapper.selectOne(goodsIdWrapper);

                if (stockInfo != null) {
                    stockInfo.setStockNum(stockInfo.getStockNum() + purInboundSub.getGoodsInNum());
                    stockInfoMapper.update(stockInfo, goodsIdWrapper);
                } else {
                    StockInfo stockInfoNew = new StockInfo();
                    stockInfoNew.setGoodsId(purInboundSub.getGoodsId());
                    stockInfoNew.setGoodsName(purInboundSub.getGoodsName());
                    stockInfoNew.setStockNum(purInboundSub.getGoodsInNum());
                    stockInfoMapper.insert(stockInfoNew);
                }
            }
        }
    }

    @Override
    public PurInbound getInboundById(Long inboundId) {
        return this.baseMapper.selectById(inboundId);
    }

    @Override
    @Transactional
    public void updateInbound(PurInbound purInbound) {
        LambdaQueryWrapper<PurInboundSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurInboundSub::getInboundCodeSub, purInbound.getInboundCode());
        purInboundSubMapper.delete(wrapper);
        if (purInbound.getPurInboundSubList() != null) {
            for (PurInboundSub inboundOrderSub : purInbound.getPurInboundSubList()) {
                purInboundSubMapper.insert(inboundOrderSub);
            }
        }
        this.baseMapper.updateById(purInbound);
    }

    @Override
    @Transactional
    public void deleteInboundById(Long inboundId) {
        PurInbound purInbound = this.baseMapper.selectById(inboundId);
        LambdaQueryWrapper<PurInboundSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurInboundSub::getInboundCodeSub, purInbound.getInboundCode());
        purInboundSubMapper.delete(wrapper);
        this.baseMapper.deleteById(inboundId);
    }
}
