package com.jnxy.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.base.entity.Goods;
import com.jnxy.base.service.IGoodsService;
import com.jnxy.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getGoodsList(@RequestParam(value = "goodsName", required = false) String goodsName,
                                                    @RequestParam(value = "pageNo") Long pageNo,
                                                    @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(goodsName), Goods::getGoodsName, goodsName);
        wrapper.orderByDesc(Goods::getGoodsId);

        Page<Goods> page = new Page<>(pageNo, pageSize);
        goodsService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addGoods(@RequestBody Goods goods) {
        goodsService.addGoods(goods);
        return Result.success("新增商品成功");
    }

    @PutMapping
    public Result<?> updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
        return Result.success("修改商品成功");
    }

    @GetMapping("/{id}")
    public Result<Goods> getGoodsById(@PathVariable("id") Long id) {
        Goods goods = goodsService.getGoodsById(id);
        return Result.success(goods);
    }

    @DeleteMapping("/{id}")
    public Result<Goods> deleteGoodsById(@PathVariable("id") Long id) {
        goodsService.deleteGoodsById(id);
        return Result.success("删除商品成功");
    }

    @GetMapping("/all")
    public Result<List<Goods>> getAllGoods() {
        List<Goods> goodsList = goodsService.list();
        return Result.success(goodsList);
    }

}
