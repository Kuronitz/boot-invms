package com.jnxy.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.stock.entity.StockInfo;
import com.jnxy.stock.mapper.StockInfoMapper;
import com.jnxy.stock.service.IStockInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 仓库信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements IStockInfoService {
    @Override
    @Transactional
    public void addStock(StockInfo stockInfo) {
        this.baseMapper.insert(stockInfo);
    }

    @Override
    public StockInfo getStockById(Long stockId) {
        return this.baseMapper.selectById(stockId);
    }

    @Override
    @Transactional
    public void updateStock(StockInfo stockInfo) {
        this.baseMapper.updateById(stockInfo);
    }

    @Override
    public void deleteStockById(Long stockId) {
        this.baseMapper.deleteById(stockId);
    }
}
