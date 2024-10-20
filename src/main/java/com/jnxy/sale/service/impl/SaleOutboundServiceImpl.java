package com.jnxy.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.common.vo.UserContext;
import com.jnxy.sale.entity.SaleOutbound;
import com.jnxy.sale.entity.SaleOutboundSub;
import com.jnxy.sale.mapper.SaleOutboundMapper;
import com.jnxy.sale.mapper.SaleOutboundSubMapper;
import com.jnxy.sale.service.ISaleOutboundService;
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
 * 销售出库单信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class SaleOutboundServiceImpl extends ServiceImpl<SaleOutboundMapper, SaleOutbound> implements ISaleOutboundService {
    @Resource
    private SaleOutboundSubMapper saleOutboundSubMapper;

    @Resource
    private StockInfoMapper stockInfoMapper;


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
    public void addOutbound(SaleOutbound saleOutbound) {
        String code = generateCode();
        saleOutbound.setOutboundCode(code);
        User user = UserContext.getCurrentUserObj();
        saleOutbound.setStkId(user.getId());
        saleOutbound.setStkName(user.getUsername());
        this.baseMapper.insert(saleOutbound);
        if (saleOutbound.getSaleOutboundSubList() != null) {
            for (SaleOutboundSub saleOutboundSub : saleOutbound.getSaleOutboundSubList()) {
                saleOutboundSub.setOutboundCodeSub(code);
                saleOutboundSubMapper.insert(saleOutboundSub);
                LambdaQueryWrapper<StockInfo> goodsIdWrapper = new LambdaQueryWrapper<>();
                goodsIdWrapper.eq(StockInfo::getGoodsId, saleOutboundSub.getGoodsId());
                StockInfo stockInfo = stockInfoMapper.selectOne(goodsIdWrapper);
                stockInfo.setStockNum(stockInfo.getStockNum() - saleOutboundSub.getGoodsOutNum());
                stockInfoMapper.update(stockInfo, goodsIdWrapper);
            }
        }
    }

    @Override
    public SaleOutbound getOutboundById(Long outboundId) {
        return this.baseMapper.selectById(outboundId);
    }

    @Override
    @Transactional
    public void updateOutbound(SaleOutbound saleOutbound) {
        LambdaQueryWrapper<SaleOutboundSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SaleOutboundSub::getOutboundCodeSub, saleOutbound.getOutboundCode());
        saleOutboundSubMapper.delete(wrapper);
        if (null != saleOutbound.getSaleOutboundSubList()) {
            for (SaleOutboundSub saleOutboundSub : saleOutbound.getSaleOutboundSubList()) {
                saleOutboundSubMapper.insert(saleOutboundSub);
            }
        }
        this.baseMapper.updateById(saleOutbound);
    }

    @Override
    @Transactional
    public void deleteOutboundById(Long outboundId) {
        SaleOutbound saleOutbound = this.baseMapper.selectById(outboundId);
        LambdaQueryWrapper<SaleOutboundSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SaleOutboundSub::getOutboundCodeSub, saleOutbound.getOutboundCode());
        saleOutboundSubMapper.delete(wrapper);
        this.baseMapper.deleteById(outboundId);
    }
}
