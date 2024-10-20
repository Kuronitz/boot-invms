package com.jnxy.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnxy.sale.entity.SaleOrderSub;

import java.util.List;

/**
 * <p>
 * 销售单信息子表 Mapper 接口
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface SaleOrderSubMapper extends BaseMapper<SaleOrderSub> {
    public List<SaleOrderSub> getSaleOrderSubListBySaleId(Long saleId);

}
