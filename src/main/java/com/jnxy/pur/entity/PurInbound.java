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
 * 采购入库单信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pur_inbound")
@ApiModel(value = "PurInbound对象", description = "采购入库单信息表")
public class PurInbound implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入库编号")
    @TableId(value = "inbound_id", type = IdType.AUTO)
    private Long inboundId;

    @ApiModelProperty("采购单单号")
    private String purchaseNum;

    @ApiModelProperty("入库编码")
    private String inboundCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("入库时间")
    private Date inboundDate;

    @ApiModelProperty("仓管编号")
    private Integer stkId;

    @ApiModelProperty("仓管名称")
    private String stkName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty(value = "采购入库单信息子表")
    @TableField(exist = false)
    private List<PurInboundSub> purInboundSubList;

}
