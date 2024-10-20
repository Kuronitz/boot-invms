package com.jnxy.sale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.sale.entity.SaleOrder;

/**
 * <p>
 * 销售单信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface ISaleOrderService extends IService<SaleOrder> {
    void addSale(SaleOrder saleOrder);

    void updateSale(SaleOrder saleOrder);

    SaleOrder getSaleById(Long saleId);

    void deleteSaleById(Long saleId);

}
