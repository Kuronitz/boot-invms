package com.jnxy.sys.controller;

import com.jnxy.common.vo.Result;
import com.jnxy.sys.entity.Menu;
import com.jnxy.sys.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
@ApiOperation("查询所有菜单数据")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @GetMapping
    public Result<List<Menu>> getAllMenu() {
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }
}
