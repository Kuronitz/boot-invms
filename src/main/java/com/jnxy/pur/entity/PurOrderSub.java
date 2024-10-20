package com.jnxy.pur.entity;

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
 * 采购单信息子表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pur_order_sub")
@ApiModel(value = "PurOrderSub对象", description = "采购单信息子表")
public class PurOrderSub implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("采购单子表编号")
    @TableId(value = "purchase_id_sub", type = IdType.AUTO)
    private Long purchaseIdSub;

    @ApiModelProperty("采购单子表单号")
    private String purchaseNumSub;

    @ApiModelProperty("商品编号")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("供应商编号")
    private Long supplierId;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("商品数量")
    private Integer goodsNum;

    @ApiModelProperty("商品进货单价")
    private Double buyingPrice;

    @ApiModelProperty("商品进货总价")
    private Double totalPrice;

}
