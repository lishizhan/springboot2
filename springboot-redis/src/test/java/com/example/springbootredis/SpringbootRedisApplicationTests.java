package com.example.springbootredis;

import com.example.springbootredis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 测试序列化时候成功
     * */
    @Test
    public void redisTest(){
        User user = new User(1231231L,"zhans", 23,"123123@qq.com");
        redisTemplate.opsForValue().set("user1",user);
        System.out.println("--------------------------");
        User user1 = (User) redisTemplate.opsForValue().get("user1");
        System.out.println("user1 = " + user1);
    }

}
