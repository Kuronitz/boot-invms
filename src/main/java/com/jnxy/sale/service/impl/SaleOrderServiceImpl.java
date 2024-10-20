package com.jnxy.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.common.vo.UserContext;
import com.jnxy.sale.entity.SaleOrder;
import com.jnxy.sale.entity.SaleOrderSub;
import com.jnxy.sale.mapper.SaleOrderMapper;
import com.jnxy.sale.mapper.SaleOrderSubMapper;
import com.jnxy.sale.service.ISaleOrderService;
import com.jnxy.stock.entity.StockInfo;
import com.jnxy.stock.mapper.StockInfoMapper;
import com.jnxy.sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 销售单信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements ISaleOrderService {

    @Resource
    private SaleOrderSubMapper saleOrderSubMapper;

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
    public void addSale(SaleOrder saleOrder) {
        Date date = new Date();
        saleOrder.setCreatedDate(date);
        String code = generateCode();
        saleOrder.setSaleNum(code);
        User user = UserContext.getCurrentUserObj();
        saleOrder.setSamId(user.getId());
        saleOrder.setSamName(user.getUsername());
        this.baseMapper.insert(saleOrder);
        if (saleOrder.getSaleOrderSubList() != null) {
            for (SaleOrderSub saleOrderSub : saleOrder.getSaleOrderSubList()) {
                LambdaQueryWrapper<StockInfo> goodsIdWrapper = new LambdaQueryWrapper<>();
                goodsIdWrapper.eq(StockInfo::getGoodsId, saleOrderSub.getGoodsId());
                StockInfo stockInfo = stockInfoMapper.selectOne(goodsIdWrapper);
                stockInfo.setSaleNum(stockInfo.getSaleNum() + saleOrderSub.getGoodsNum());
                stockInfoMapper.update(stockInfo, goodsIdWrapper);

                saleOrderSub.setTotalPrice(saleOrderSub.getGoodsNum() * saleOrderSub.getSellingPrice());
                saleOrderSub.setSaleNumSub(code);
                saleOrderSubMapper.insert(saleOrderSub);
            }
        }
    }

    @Override
    public SaleOrder getSaleById(Long saleId) {
        SaleOrder saleOrder = this.baseMapper.selectById(saleId);
        List<SaleOrderSub> sublist = saleOrderSubMapper.getSaleOrderSubListBySaleId(saleId);
        saleOrder.setSaleOrderSubList(sublist);
        return saleOrder;
    }

    @Override
    @Transactional
    public void updateSale(SaleOrder saleOrder) {
        this.baseMapper.updateById(saleOrder);
        LambdaQueryWrapper<SaleOrderSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SaleOrderSub::getSaleNumSub, saleOrder.getSaleNum());
        saleOrderSubMapper.delete(wrapper);
        if (null != saleOrder.getSaleOrderSubList()) {
            for (SaleOrderSub saleOrderSub : saleOrder.getSaleOrderSubList()) {
                saleOrderSubMapper.insert(saleOrderSub);
            }
        }
    }

    @Override
    @Transactional
    public void deleteSaleById(Long saleId) {
        SaleOrder sale = this.baseMapper.selectById(saleId);
        LambdaQueryWrapper<SaleOrderSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SaleOrderSub::getSaleNumSub, sale.getSaleNum());
        saleOrderSubMapper.delete(wrapper);
        this.baseMapper.deleteById(saleId);
    }
}
