package com.jnxy.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.base.entity.Goods;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface IGoodsService extends IService<Goods> {

    void addGoods(Goods goods);

    void updateGoods(Goods goods);

    Goods getGoodsById(Long goodsId);

    void deleteGoodsById(Long goodsId);
}
