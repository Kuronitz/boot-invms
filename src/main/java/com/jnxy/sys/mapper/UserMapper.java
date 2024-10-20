package com.jnxy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnxy.sys.entity.User;

import java.util.List;

/**
 * <p>
 * 系统用户信息表 Mapper 接口
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface UserMapper extends BaseMapper<User> {
    public List<String> getRoleNameByUserId(Integer userId);
}
