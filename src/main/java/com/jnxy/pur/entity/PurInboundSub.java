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
 * 采购入库单信息子表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pur_inbound_sub")
@ApiModel(value = "PurInboundSub对象", description = "采购入库单信息子表")
public class PurInboundSub implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入库商品子表编号")
    @TableId(value = "inbound_id_sub", type = IdType.AUTO)
    private Long inboundIdSub;

    @ApiModelProperty("入库商品子表编码")
    private String inboundCodeSub;

    @ApiModelProperty("商品编号")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品类别")
    private String goodsType;

    @ApiModelProperty("商品单位")
    private String goodsUnit;

    @ApiModelProperty("商品入库数量")
    private Long goodsInNum;

}
