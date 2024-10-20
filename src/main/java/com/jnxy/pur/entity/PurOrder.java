package com.jnxy.pur.entity;

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
 * 采购单信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pur_order")
@ApiModel(value = "PurOrder对象", description = "采购单信息表")
public class PurOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("采购单编号")
    @TableId(value = "purchase_id", type = IdType.AUTO)
    private Long purchaseId;

    @ApiModelProperty("采购单单号")
    private String purchaseNum;

    @ApiModelProperty("采购员编号")
    private Integer merId;

    @ApiModelProperty("采购员名称")
    private String merName;

    @ApiModelProperty("描述")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("采购单创建日期")
    private Date createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("商品采购日期")
    private Date inboundDate;

    @ApiModelProperty("仓管编号")
    private Integer stkId;

    @ApiModelProperty("仓管名称")
    private String stkName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty(value = "采购单信息子表")
    @TableField(exist = false)
    private List<PurOrderSub> purOrderSubList;

}
