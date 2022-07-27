package com.example.springbootredis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.springbootredis.entity.User;
import com.example.springbootredis.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 该类测试redis的序列化，它的序列化方式有两种：
 *  1，配置序列化器，修改序列化器为RedisTemplate序列化器为GenericJackson2JsonRedisSerializer
 *      缺点：占用缓存内存，每个都携带类信息
 *  2，使用StringRedisTemplate序列化 不需要配置
 *     写入redis时候手动把对象序列化为json
 *     读取是手动把json进行反序列化
 *
 * */
@RestController
@RequestMapping("api")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/string")
    public String string(String key, String value) {
        log.info("[UserController] [string] key={},value={}", key, value);
        redisTemplate.opsForValue().set(key, value);
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }


    /**
     * 没有使用redis序列化，使用String进行序列化，手动进行序列化与反序列化
     * <p>
     * 缓存的基本使用：
     * 1，查询缓存是否命中
     * ->|命中直接返回
     * ->|不命中直接查询数据库，之后在添加缓存
     * <p>
     * 2，缓存所产生的问题：
     * 缓存穿透：
     * 缓存击穿：
     * 缓存雪崩：
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> users() {
        String users = redisTemplate.opsForValue().get("user:users");
        log.info("[UserController] [users] 缓存是否命中：{}", users);
        if (users == null) {
            //缓存中无数据，查询数据库
            List<User> userList = userService.getUsers();
            //数据转为String
            String s = JSON.toJSONString(userList);
            redisTemplate.opsForValue().set("user:users", s);
            return userList;
        }
        List<User> userList = JSON.parseObject(users, new TypeReference<List<User>>() {
        });
        return userList;
    }

    public static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/user")
    public User user(User user) throws JsonProcessingException {
        //手动序列化
        String u = mapper.writeValueAsString(user);
        log.info("手动序列化--->{}",u);
        //保存到redis
        redisTemplate.opsForValue().set("user:" + user.getId(), u);
        //从redis中获取
        String s = redisTemplate.opsForValue().get("user:" + user.getId());
        //手动反序列化
        User value = mapper.readValue(s, User.class);
        log.info("手动反序列化--->{}",value);
        return value;

    }


    @Autowired
    private RedisTemplate<String, Object> objectRedisTemple;

    /**
     * 测试序列化后存储一个对象
     * 保存一个用户对象到redis
     */
    @PostMapping("/insertUserToRedis")
    public User insertRedis(User user) {
        log.info("user--->{}", user);
        objectRedisTemple.opsForValue().set("user:" + user.getId(), user);
        User u = (User) objectRedisTemple.opsForValue().get("user:" + user.getId());
        return u;
    }

    /**
     * 序列话后存储一个list
     */
    @GetMapping("/getUser")
    public List<User> getUser() {
        List<User> o = (List<User>) objectRedisTemple.opsForValue().get("user:list");
        if (o == null || o.size() == 0) {
            //缓存无数据
            List<User> users = userService.getUsers();
            //出入redis
            objectRedisTemple.opsForValue().set("user:list", users);
            return users;
        }
        return o;
    }


    /**
     * 测试RedisTemplate opsForHash()
     * */
    @GetMapping("/userHash")
    public User userHash(){
        redisTemplate.opsForHash();
        return null;
    }

    /*
    * 测试session
    * */
    @GetMapping("testSession")
    public String testSession(HttpServletRequest request){
        request.getSession().setAttribute("user",new User());
        return "SUCCESS";
    }
    @GetMapping("getSessionId")
    public String getSessionId(HttpServletRequest request){
        User session = (User) request.getSession().getAttribute("user");
        return "SUCCESS--->"+session;
    }


}
