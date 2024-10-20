package com.jnxy.stock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 仓库信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stock_info")
@ApiModel(value = "StockInfo对象", description = "仓库信息表")
public class StockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("仓库编号")
    @TableId(value = "stock_id", type = IdType.AUTO)
    private Long stockId;

    @ApiModelProperty("商品编号")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("库存数量")
    private Long stockNum;

    @ApiModelProperty("销售数量")
    private Long saleNum;

    @ApiModelProperty("上次进货单价")
    private Double lastBuyingPrice;

    @ApiModelProperty("平均进价")
    private Double avgBuyingPrice;

    @ApiModelProperty("标准卖价")
    private Double sellingPrice;

    @ApiModelProperty("库存总值")
    private Double totalValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("生产日期")
    private Date manuFacDate;

    @ApiModelProperty("商品有效期")
    private String validityDate;

    @ApiModelProperty("是否被禁用")
    private Integer deleted;
}
