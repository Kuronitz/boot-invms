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
 * 供应商信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_supplier")
@ApiModel(value = "Supplier对象", description = "供应商信息表")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("供应商编号")
    @TableId(value = "supplier_id", type = IdType.AUTO)
    private Long supplierId;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系人手机")
    private String contactPhone;

    @ApiModelProperty("供应商联系地址")
    private String contactAddress;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("供应商状态")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建日期")
    private Date createdDate;

    @ApiModelProperty("是否被禁用")
    private Integer deleted;

}
