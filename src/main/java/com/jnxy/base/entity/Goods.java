package com.jnxy.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_goods")
@ApiModel(value = "Goods对象", description = "商品信息表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品编号")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品编码")
    private String goodsCode;

    @ApiModelProperty("商品类别")
    private String goodsType;

    @ApiModelProperty("商品品牌")
    private String goodsBrand;

    @ApiModelProperty("商品单位")
    private String goodsUnit;

    @ApiModelProperty("标准买价")
    private Double buyingPrice;

    @ApiModelProperty("标准卖价")
    private Double sellingPrice;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建日期")
    private Date createdDate;

    @ApiModelProperty("是否被禁用")
    private Integer deleted;

}
