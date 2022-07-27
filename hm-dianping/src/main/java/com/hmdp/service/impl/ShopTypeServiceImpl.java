package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@Slf4j
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<ShopType> queryTypeList() {
        //1，查询缓存中是否有店铺例表数据
        String shopTypeJson = redisTemplate.opsForValue().get(RedisConstants.CACHE_SHOP_TYPE_LIST_KEY);
        if (StrUtil.isBlank(shopTypeJson)){
            //2，缓存没有该数据查询数据库 typeService.query().orderByAsc("sort").list();
            List<ShopType> shopTypeList = this.lambdaQuery().orderByAsc(ShopType::getSort).list();
            log.info("shopTypeList----->{}",shopTypeList );
            //3，将查询数据添加进缓存
            String jsonStr = JSONUtil.toJsonStr(shopTypeList);
            redisTemplate.opsForValue().set(RedisConstants.CACHE_SHOP_TYPE_LIST_KEY,jsonStr,RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
            //4，返回
            return shopTypeList;
        }
        //5，缓存中有数据
        List<ShopType> typeList = JSONUtil.toList(shopTypeJson, ShopType.class);
        return typeList;
    }
}
