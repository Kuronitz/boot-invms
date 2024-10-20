package com.jnxy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.sys.entity.Menu;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenu();

    List<Menu> getMenuListByUserId(Integer userId);
}
