package com.jnxy.sale.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.common.vo.Result;
import com.jnxy.sale.entity.SaleOutbound;
import com.jnxy.sale.service.ISaleOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售出库单信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */

@RestController
@RequestMapping("/saleOutbound")
public class SaleOutboundController {

    @Autowired
    private ISaleOutboundService saleOutboundService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getOutboundList(@RequestParam(value = "outboundCode", required = false) String outboundCode,
                                                       @RequestParam(value = "pageNo") Long pageNo,
                                                       @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<SaleOutbound> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(outboundCode), SaleOutbound::getOutboundCode, outboundCode);
        wrapper.orderByDesc(SaleOutbound::getOutboundId);

        Page<SaleOutbound> page = new Page<>(pageNo, pageSize);
        saleOutboundService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addOutbound(@RequestBody SaleOutbound saleOutbound) {
        saleOutboundService.addOutbound(saleOutbound);
        return Result.success("新增出库信息成功");
    }

    @PutMapping
    public Result<?> updateOutbound(@RequestBody SaleOutbound saleOutbound) {
        saleOutboundService.updateOutbound(saleOutbound);
        return Result.success("修改出库信息成功");
    }

    @GetMapping("/{id}")
    public Result<SaleOutbound> getOutboundById(@PathVariable("id") Long id) {
        SaleOutbound saleOutbound = saleOutboundService.getOutboundById(id);
        return Result.success(saleOutbound);
    }

    @DeleteMapping("/{id}")
    public Result<SaleOutbound> deleteOutboundById(@PathVariable("id") Long id) {
        saleOutboundService.deleteOutboundById(id);
        return Result.success("删除销售单成功");
    }

    @GetMapping("/all")
    public Result<List<SaleOutbound>> getAllOutbound() {
        List<SaleOutbound> saleOutboundList = saleOutboundService.list();
        return Result.success(saleOutboundList);
    }
}
