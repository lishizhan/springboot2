package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.JAXBUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 尝试获取锁
     */
    private boolean tryLock(String key) {
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(aBoolean);
    }

    /**
     * 释放锁
     */
    private void unLock(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 利用互斥锁解决缓存击穿问题
     */
    @Override
    public Result queryShopById(Long id) {
        //缓存穿透
        //Shop shop = queryWithPassThrough(id);
        //缓存击穿
        Shop shop = queryWithMutex(id);
        if (shop == null) {
            return Result.fail("店铺不存在！");
        }
        return Result.ok(shop);
    }

    /**
     * 缓存j击穿的解决方法，使用互斥锁
     */
    private Shop queryWithMutex(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        //1，从redis查询缓存是否存在
        String shopJson = redisTemplate.opsForValue().get(key);
        //2，判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3，存在直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }
        //防止缓存穿透，判断是否为空值
        if (shopJson != null) {
            return null;
        }
        //-----------------------------------------------------
        //4，开始实现缓存从建
        //4.1，获取互斥锁
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        Shop shop = null;
        try {
            boolean isLock = tryLock(lockKey);
            //4.2，判断是否获取成功
            if (!isLock) {
                //4.3，失败则休眠
                Thread.sleep(50);
                return queryWithMutex(id);
            }
            //4.4，不存在根据id查询数据库
            shop = this.getById(id);
            if (shop == null) {
                //5，数据库中无记录返回错误
                //缓存空值避免缓存穿透
                redisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            //6，数据库存在则写入redis
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //释放互斥锁
            unLock(lockKey);
        }

        //7，返回
        return shop;
    }


    /**
     * 缓存穿透的解决方法，缓存空值
     */
    private Shop queryWithPassThrough(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        //1，从redis查询缓存是否存在
        String shopJson = redisTemplate.opsForValue().get(key);
        //2，判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3，存在直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }
        //防止缓存穿透，判断是否为空值
        if (shopJson != null) {
            return null;
        }
        //4，不存在根据id查询数据库
        Shop shop = this.getById(id);
        if (shop == null) {
            //5，数据库中无记录返回错误
            //缓存空值避免缓存穿透
            redisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        //6，数据库存在则写入redis
        redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        //7，返回
        return shop;
    }


    /**
     * 加入缓存实现店铺查询，此方法已解决缓存穿透，但未解决缓存击穿的问题
     */
//    @Override
    public Result queryShopById123(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        //1，从redis查询缓存是否存在
        String shopJson = redisTemplate.opsForValue().get(key);
        //2，判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3，存在直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return Result.ok(shop);
        }
        //防止缓存穿透，判断是否为空值
        if (shopJson != null) {
            return Result.fail("店铺信息不存在！！");
        }
        //4，不存在根据id查询数据库
        Shop shop = this.getById(id);
        if (shop == null) {
            //5，数据库中无记录返回错误
            //缓存空值避免缓存穿透
            redisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return Result.fail("店铺不存在");
        }
        //6，数据库存在则写入redis
        redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        //7，返回
        return Result.ok(shop);
    }


    /**
     * 数据库与缓存的一致性问题
     */
    @Override
    @Transactional
    public Result update(Shop shop) {
        if (shop.getId() == null) return Result.fail("店铺ID不能为空");
        //1，跟新数据库
        this.updateById(shop);
        //2，删除缓存
        redisTemplate.delete(RedisConstants.CACHE_SHOP_KEY + shop.getId());

        return Result.ok();
    }
}
