package com.example.springbootredis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    private Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("192.168.8.200", 6379);
        jedis.auth("lsz!@#$redis");
    }

    @Test
    public void JedisTest() {
        String ping = jedis.ping();
        System.out.println("ping = " + ping);
    }

    /**
     * 测试String
     */
    @Test
    public void stringTest() {
        String set = jedis.set("name", "zhans");
        System.out.println("set = " + set);
        String name = jedis.get("name");
        System.out.println("name = " + name);
        jedis.expire("name", 30);
        long ttl = jedis.ttl("name");
        System.out.println("ttl = " + ttl);
        long strlen = jedis.strlen("name");
        System.out.println("strlen = " + strlen);
    }

    /**
     * list
     */
    @Test
    public void listTest() {
        long lpush = jedis.lpush("city", "beijing", "shanghai", "广州");
        System.out.println("lpush = " + lpush);
        List<String> citys = jedis.lrange("city", 0, -1);
        citys.forEach(city -> System.out.println("city = " + city));
    }

    /**
     * set
     */
    @Test
    public void setTest() {
        long sadd = jedis.sadd("hobby", "游泳", "rap", "篮球");
        System.out.println("sadd = " + sadd);
        Set<String> smembers = jedis.smembers("hobby");
        for (String smember : smembers) {
            System.out.println("smember = " + smember);
        }
    }

    /**
     * hash
     */
    @Test
    public void hashTest() {
        jedis.hset("user:1001", "name", "zhans");
        jedis.hset("user:1001", "age", "24");
        jedis.hset("user:1001", "sex", "男");
        Map<String, String> all = jedis.hgetAll("user:1001");
        all.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });

    }


    @After
    public void after() {
        jedis.close();
    }


}
