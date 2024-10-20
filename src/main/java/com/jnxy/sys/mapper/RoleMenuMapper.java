package com.jnxy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnxy.sys.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * 系统角色菜单映射表 Mapper 接口
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    public List<Integer> getMenuIdListByRoleId(Integer roleId);

}
