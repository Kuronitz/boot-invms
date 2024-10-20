package com.jnxy.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.base.entity.Supplier;
import com.jnxy.base.service.ISupplierService;
import com.jnxy.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getSupplierList(@RequestParam(value = "supplierName", required = false) String supplierName,
                                                       @RequestParam(value = "pageNo") Long pageNo,
                                                       @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(supplierName), Supplier::getSupplierName, supplierName);
        wrapper.orderByDesc(Supplier::getSupplierId);

        Page<Supplier> page = new Page<>(pageNo, pageSize);
        supplierService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addSupplier(@RequestBody Supplier supplier) {
        supplierService.addSupplier(supplier);
        return Result.success("新增供应商成功");
    }

    @PutMapping
    public Result<?> updateSupplier(@RequestBody Supplier supplier) {
        supplierService.updateSupplier(supplier);
        return Result.success("修改供应商成功");
    }

    @GetMapping("/{id}")
    public Result<Supplier> getSupplierById(@PathVariable("id") Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return Result.success(supplier);
    }

    @DeleteMapping("/{id}")
    public Result<Supplier> deleteSupplierById(@PathVariable("id") Long id) {
        supplierService.deleteSupplierById(id);
        return Result.success("删除供应商成功");
    }

    @GetMapping("/all")
    public Result<List<Supplier>> getAllSupplier() {
        List<Supplier> supplierList = supplierService.list();
        return Result.success(supplierList);
    }

}
