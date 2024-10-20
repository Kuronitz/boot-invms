package com.jnxy.pur.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.pur.entity.PurOrder;

/**
 * <p>
 * 采购单信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface IPurOrderService extends IService<PurOrder> {
    void addPurchase(PurOrder purOrder);

    void updatePurchase(PurOrder purOrder);

    PurOrder getPurchaseById(Long purchaseId);

    void deletePurchaseById(Long purchaseId);
}
