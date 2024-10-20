package com.jnxy.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "系统菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单编号")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty("映射组件")
    private String component;

    @ApiModelProperty("链接路径")
    private String path;

    @ApiModelProperty("重定向")
    private String redirect;

    @ApiModelProperty("路由名称")
    private String name;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("指定父组件")
    private Integer parentId;

    @ApiModelProperty("子节点判断")
    private String isLeaf;

    @ApiModelProperty("隐藏")
    private Boolean hidden;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> meta;

    public Map<String, Object> getMeta() {
        meta = new HashMap<>();
        meta.put("title", title);
        meta.put("icon", icon);
        return meta;
    }

}
