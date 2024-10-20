package com.jnxy.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 销售单信息子表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sale_order_sub")
@ApiModel(value = "SaleOrderSub对象", description = "销售单信息子表")
public class SaleOrderSub implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("销售单子表编号")
    @TableId(value = "sale_id_sub", type = IdType.AUTO)
    private Long saleIdSub;

    @ApiModelProperty("订单号子表")
    private String saleNumSub;

    @ApiModelProperty("销售订单商品编号")
    private Long goodsId;

    @ApiModelProperty("销售订单商品名称")
    private String goodsName;

    @ApiModelProperty("销售订单商品数量")
    private Long goodsNum;

    @ApiModelProperty("商品销售单价")
    private Double sellingPrice;

    @ApiModelProperty("商品销售总价")
    private Double totalPrice;

}
