package com.jnxy.stock.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnxy.common.vo.Result;
import com.jnxy.stock.entity.StockInfo;
import com.jnxy.stock.service.IStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库信息表 前端控制器
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/stock")
public class StockInfoController {

    @Autowired
    private IStockInfoService stockService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getStockList(@RequestParam(value = "goodsName", required = false) String goodsName,
                                                    @RequestParam(value = "pageNo") Long pageNo,
                                                    @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<StockInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(goodsName), StockInfo::getGoodsName, goodsName);
        wrapper.orderByDesc(StockInfo::getStockId);

        Page<StockInfo> page = new Page<>(pageNo, pageSize);
        stockService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addStock(@RequestBody StockInfo stockInfo) {
        stockService.addStock(stockInfo);
        return Result.success("新增库存成功");
    }

    @PutMapping
    public Result<?> updateStock(@RequestBody StockInfo stockInfo) {
        stockInfo.setTotalValue(stockInfo.getSaleNum() * stockInfo.getSellingPrice());
        stockService.updateStock(stockInfo);
        return Result.success("修改库存成功");
    }

    @GetMapping("/{id}")
    public Result<StockInfo> getStockById(@PathVariable("id") Long id) {
        StockInfo stock = stockService.getStockById(id);
        return Result.success(stock);
    }

    @DeleteMapping("/{id}")
    public Result<StockInfo> deleteStockById(@PathVariable("id") Long id) {
        stockService.deleteStockById(id);
        return Result.success("删除库存成功");
    }

    @GetMapping("/all")
    public Result<List<StockInfo>> getAllStock() {
        List<StockInfo> stockList = stockService.list();
        return Result.success(stockList);
    }

}
