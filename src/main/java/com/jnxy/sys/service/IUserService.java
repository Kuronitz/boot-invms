package com.jnxy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.sys.entity.User;

import java.util.Map;

/**
 * <p>
 * 系统用户信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface IUserService extends IService<User> {
    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    void addUser(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
