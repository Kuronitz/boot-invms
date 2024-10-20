package com.jnxy.pur.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.common.vo.Result;
import com.jnxy.pur.entity.PurInbound;
import com.jnxy.pur.service.IPurInboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购入库单信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/purInbound")
public class PurInboundController {

    @Autowired
    private IPurInboundService purInboundService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getInboundList(@RequestParam(value = "inboundCode", required = false) String inboundCode,
                                                      @RequestParam(value = "pageNo") Long pageNo,
                                                      @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<PurInbound> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(inboundCode), PurInbound::getInboundCode, inboundCode);
        wrapper.orderByDesc(PurInbound::getInboundId);

        Page<PurInbound> page = new Page<>(pageNo, pageSize);
        purInboundService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addInbound(@RequestBody PurInbound purInbound) {
        purInboundService.addInbound(purInbound);
        return Result.success("新增入库信息成功");
    }

    @PutMapping
    public Result<?> updateInbound(@RequestBody PurInbound purInbound) {
        purInboundService.updateInbound(purInbound);
        return Result.success("修改入库信息成功");
    }

    @GetMapping("/{id}")
    public Result<PurInbound> getInboundById(@PathVariable("id") Long id) {
        PurInbound purInbound = purInboundService.getInboundById(id);
        return Result.success(purInbound);
    }

    @DeleteMapping("/{id}")
    public Result<PurInbound> deleteInboundById(@PathVariable("id") Long id) {
        purInboundService.deleteInboundById(id);
        return Result.success("删除入库信息成功");
    }

    @GetMapping("/all")
    public Result<List<PurInbound>> getAllInbound() {
        List<PurInbound> purInboundList = purInboundService.list();
        return Result.success(purInboundList);
    }
}
