package com.jnxy.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户信息表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@ApiModel(value = "User对象", description = "系统用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户电话")
    private String phone;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户删除")
    private Integer deleted;

    @TableField(exist = false)
    private List<Integer> roleIdList;
}
