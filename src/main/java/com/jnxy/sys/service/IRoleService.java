package com.jnxy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.sys.entity.Role;

/**
 * <p>
 * 系统角色信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);
}
