package com.jnxy.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;

/**
 * <p>
 * 销售单信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sale_order")
@ApiModel(value = "SaleOrder对象", description = "销售单信息表")
public class SaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("销售单编号")
    @TableId(value = "sale_id", type = IdType.AUTO)
    private Long saleId;

    @ApiModelProperty("销售订单号")
    private String saleNum;

    @ApiModelProperty("销售员编号")
    private Integer samId;

    @ApiModelProperty("销售员名称")
    private String samName;

    @ApiModelProperty("描述")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("销售单创建日期")
    private Date createdDate;

    @ApiModelProperty("销售单信息子表")
    @TableField(exist = false)
    private List<SaleOrderSub> saleOrderSubList;

}
