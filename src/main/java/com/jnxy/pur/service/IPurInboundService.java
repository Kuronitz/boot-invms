package com.jnxy.pur.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.pur.entity.PurInbound;

/**
 * <p>
 * 采购入库单信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface IPurInboundService extends IService<PurInbound> {
    void addInbound(PurInbound purInbound);

    void updateInbound(PurInbound purInbound);

    PurInbound getInboundById(Long inboundId);

    void deleteInboundById(Long inboundId);
}
