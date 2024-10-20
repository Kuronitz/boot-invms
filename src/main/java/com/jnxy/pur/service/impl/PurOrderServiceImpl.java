package com.jnxy.pur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.common.vo.UserContext;
import com.jnxy.pur.entity.PurOrder;
import com.jnxy.pur.entity.PurOrderSub;
import com.jnxy.pur.mapper.PurOrderMapper;
import com.jnxy.pur.mapper.PurOrderSubMapper;
import com.jnxy.pur.service.IPurOrderService;
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
 * 采购单信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class PurOrderServiceImpl extends ServiceImpl<PurOrderMapper, PurOrder> implements IPurOrderService {
    @Resource
    private PurOrderSubMapper purOrderSubMapper;

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
    public void addPurchase(PurOrder purOrder) {
        purOrder.setStatus(0);
        Date date = new Date();
        purOrder.setCreatedDate(date);
        String code = generateCode();
        purOrder.setPurchaseNum(code);
        User user = UserContext.getCurrentUserObj();
        purOrder.setMerId(user.getId());
        purOrder.setMerName(user.getUsername());
        this.baseMapper.insert(purOrder);
        if (purOrder.getPurOrderSubList() != null) {
            for (PurOrderSub purOrderSub : purOrder.getPurOrderSubList()) {
                purOrderSub.setTotalPrice(purOrderSub.getGoodsNum() * purOrderSub.getBuyingPrice());
                purOrderSub.setPurchaseNumSub(code);
                purOrderSubMapper.insert(purOrderSub);
            }
        }
    }

    @Override
    public PurOrder getPurchaseById(Long purchaseId) {
        PurOrder purOrder = this.baseMapper.selectById(purchaseId);
        List<PurOrderSub> sublist = purOrderSubMapper.getPurOrderSubListByPurId(purchaseId);
        purOrder.setPurOrderSubList(sublist);
        return purOrder;
    }

    @Override
    @Transactional
    public void updatePurchase(PurOrder purOrder) {
        this.baseMapper.updateById(purOrder);
        LambdaQueryWrapper<PurOrderSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurOrderSub::getPurchaseNumSub, purOrder.getPurchaseNum());
        purOrderSubMapper.delete(wrapper);
        if (null != purOrder.getPurOrderSubList()) {
            for (PurOrderSub purOrderSub : purOrder.getPurOrderSubList()) {
                purOrderSubMapper.insert(purOrderSub);
            }
        }
    }

    @Override
    @Transactional
    public void deletePurchaseById(Long purchaseId) {
        PurOrder purchase = this.baseMapper.selectById(purchaseId);
        LambdaQueryWrapper<PurOrderSub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurOrderSub::getPurchaseNumSub, purchase.getPurchaseNum());
        purOrderSubMapper.delete(wrapper);
        this.baseMapper.deleteById(purchaseId);
    }
}
