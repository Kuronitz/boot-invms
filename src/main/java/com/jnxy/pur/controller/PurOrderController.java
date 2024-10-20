package com.jnxy.pur.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.common.vo.Result;
import com.jnxy.pur.entity.PurOrder;
import com.jnxy.pur.service.IPurOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购单信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/purOrder")
public class PurOrderController {

    @Autowired
    private IPurOrderService purchaseService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getPurchaseList(@RequestParam(value = "purchaseNum", required = false) String purchaseNum,
                                                       @RequestParam(value = "pageNo") Long pageNo,
                                                       @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<PurOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(purchaseNum), PurOrder::getPurchaseNum, purchaseNum);
        wrapper.orderByDesc(PurOrder::getPurchaseId);

        Page<PurOrder> page = new Page<>(pageNo, pageSize);
        purchaseService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addPurchase(@RequestBody PurOrder purOrder) {
        purchaseService.addPurchase(purOrder);
        return Result.success("新增采购单成功");
    }

    @PutMapping
    public Result<?> updatePurchase(@RequestBody PurOrder purOrder) {
        purchaseService.updatePurchase(purOrder);
        return Result.success("修改采购单成功");
    }

    @GetMapping("/{id}")
    public Result<PurOrder> getPurchaseById(@PathVariable("id") Long id) {
        PurOrder purOrder = purchaseService.getPurchaseById(id);
        return Result.success(purOrder);
    }

    @DeleteMapping("/{id}")
    public Result<PurOrder> deletePurchaseById(@PathVariable("id") Long id) {
        purchaseService.deletePurchaseById(id);
        return Result.success("删除采购单成功");
    }

    @GetMapping("/all")
    public Result<List<PurOrder>> getAllPurchase() {
        List<PurOrder> purOrderList = purchaseService.list();
        return Result.success(purOrderList);
    }

}
