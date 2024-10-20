package com.jnxy.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.base.entity.Goods;
import com.jnxy.base.mapper.GoodsMapper;
import com.jnxy.base.service.IGoodsService;
import com.jnxy.common.vo.UserContext;
import com.jnxy.sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    public static String generateCode() {
        String date = new SimpleDateFormat("yyMMdd").format(new Date());
        StringBuilder dateStr = new StringBuilder(date);
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        int sum = random.nextInt(max) % (max - min + 1) + min;
        dateStr.append(sum);
        return dateStr.toString().toLowerCase();
    }

    @Override
    @Transactional
    public void addGoods(Goods goods) {
        goods.setGoodsCode(generateCode());
        User user = UserContext.getCurrentUserObj();
        goods.setCreatedUser(user.getUsername());
        Date createdDate = new Date();
        goods.setCreatedDate(createdDate);
        this.baseMapper.insert(goods);
    }

    @Override
    public Goods getGoodsById(Long goodsId) {
        return this.baseMapper.selectById(goodsId);
    }

    @Override
    @Transactional
    public void updateGoods(Goods goods) {
        this.baseMapper.updateById(goods);
    }

    @Override
    public void deleteGoodsById(Long goodsId) {
        this.baseMapper.deleteById(goodsId);
    }
}
