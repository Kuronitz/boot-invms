package com.jnxy.sale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.sale.entity.SaleOutbound;

/**
 * <p>
 * 销售出库单信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface ISaleOutboundService extends IService<SaleOutbound> {
    void addOutbound(SaleOutbound saleOutbound);

    void updateOutbound(SaleOutbound saleOutbound);

    SaleOutbound getOutboundById(Long outboundId);

    void deleteOutboundById(Long outboundId);
}
