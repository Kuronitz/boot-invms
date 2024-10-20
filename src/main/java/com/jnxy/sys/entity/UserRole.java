package com.jnxy.sys.entity;

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
 * 系统用户角色映射表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
@ApiModel(value = "UserRole对象", description = "系统用户角色映射表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("映射序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户编号")
    private Integer userId;

    @ApiModelProperty("角色编号")
    private Integer roleId;
}
