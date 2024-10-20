package com.jnxy.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.stock.entity.StockInfo;

/**
 * <p>
 * 仓库信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface IStockInfoService extends IService<StockInfo> {
    void addStock(StockInfo stockInfo);

    void updateStock(StockInfo stockInfo);

    StockInfo getStockById(Long stockId);

    void deleteStockById(Long stockId);
}
