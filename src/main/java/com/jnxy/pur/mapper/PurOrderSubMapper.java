package com.jnxy.pur.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnxy.pur.entity.PurOrderSub;

import java.util.List;

/**
 * <p>
 * 采购单信息子表 Mapper 接口
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface PurOrderSubMapper extends BaseMapper<PurOrderSub> {

    public List<PurOrderSub> getPurOrderSubListByPurId(Long purchaseId);
}
