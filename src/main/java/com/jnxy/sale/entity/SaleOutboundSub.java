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
 * 销售出库单信息子表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sale_outbound_sub")
@ApiModel(value = "SaleOutboundSub对象", description = "销售出库单信息子表")
public class SaleOutboundSub implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("出库子表编号")
    @TableId(value = "outbound_id_sub", type = IdType.AUTO)
    private Long outboundIdSub;

    @ApiModelProperty("出库子表编码")
    private String outboundCodeSub;

    @ApiModelProperty("出库商品编号")
    private Long goodsId;

    @ApiModelProperty("出库商品名称")
    private String goodsName;

    @ApiModelProperty("出库商品类别")
    private String goodsType;

    @ApiModelProperty("商品单位")
    private String goodsUnit;

    @ApiModelProperty("商品出库数量")
    private Long goodsOutNum;

}
