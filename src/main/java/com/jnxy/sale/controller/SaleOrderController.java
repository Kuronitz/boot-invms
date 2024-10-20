package com.jnxy.sale.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.common.vo.Result;
import com.jnxy.sale.entity.SaleOrder;
import com.jnxy.sale.service.ISaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {

    @Autowired
    private ISaleOrderService saleService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getSaleList(@RequestParam(value = "saleNum", required = false) String saleNum,
                                                   @RequestParam(value = "pageNo") Long pageNo,
                                                   @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<SaleOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(saleNum), SaleOrder::getSaleNum, saleNum);
        wrapper.orderByDesc(SaleOrder::getSaleId);

        Page<SaleOrder> page = new Page<>(pageNo, pageSize);
        saleService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addSale(@RequestBody SaleOrder saleOrder) {
        saleService.addSale(saleOrder);
        return Result.success("新增销售单成功");
    }

    @PutMapping
    public Result<?> updateSale(@RequestBody SaleOrder saleOrder) {
        saleService.updateSale(saleOrder);
        return Result.success("修改销售单成功");
    }

    @GetMapping("/{id}")
    public Result<SaleOrder> getSaleById(@PathVariable("id") Long id) {
        SaleOrder saleOrder = saleService.getSaleById(id);
        return Result.success(saleOrder);
    }

    @DeleteMapping("/{id}")
    public Result<SaleOrder> deleteSaleById(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id);
        return Result.success("删除销售单成功");
    }

    @GetMapping("/all")
    public Result<List<SaleOrder>> getAllSale() {
        List<SaleOrder> saleList = saleService.list();
        return Result.success(saleList);
    }
}
