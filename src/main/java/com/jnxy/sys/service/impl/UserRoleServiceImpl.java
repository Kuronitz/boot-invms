package com.jnxy.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.sys.entity.UserRole;
import com.jnxy.sys.mapper.UserRoleMapper;
import com.jnxy.sys.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户角色映射表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
