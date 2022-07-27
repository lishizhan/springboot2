package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */

/*
 * 使用redis解决session的共享问题
 *
 * */

@Service("sessionRedisUserServiceImpl")
@Slf4j
public class UserServiceISessionRedisImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * redis的实现方式验证码验证方式
     */
    @Override
    public Result sendCode(String phone, HttpSession session) {
        //1，校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            //2，手机号不符合，返回错误消息
            return Result.fail("手机号格式不正确");
        }
        //3，符合生成验证码
        String code = RandomUtil.randomNumbers(6);
        //4，保存验证码到redis
//        session.setAttribute("code",code);
        redisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + phone, code, RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //5，发送验证码，这个不先做
        log.debug("发送短信验证码成功-->{}", code);
        //6，返回消息
        return Result.ok();
    }

    /**
     * redis的实现方式验证码验证方式
     * 短信登陆与注册一致
     */
    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1，校验手机号
        if (RegexUtils.isPhoneInvalid(loginForm.getPhone())) {
            //1.1，手机号不符合，返回错误消息
            return Result.fail("手机号格式不正确");
        }
        //2，校验验证码
        //2.1 从redis中取出验证码，注意不要硬编码，应该定义为常量
//        String code = (String) session.getAttribute("code");
        String formCode = loginForm.getCode();
        String code = redisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + loginForm.getPhone());
        if (formCode == null || !formCode.equals(code)) {
            //3，不一致报错
            return Result.fail("验证码错误");
        }
        //4，一致根据手机号查询数据库
        //User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, loginForm.getPhone()));
        User user = lambdaQuery().eq(User::getPhone, loginForm.getPhone()).one();
        //5，判断用户是否存在
        if (user == null) {
            //6，不存在根据手机号创建用户
            user = createUserWithPhone(loginForm.getPhone());
        }
        //7，保存用户信息到redis
//        session.setAttribute("user", user);
        //7.1生成token 作为登陆令牌
        String token = UUID.randomUUID().toString(true);
        //7.2将User对象作为hashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((filedName,filedValue)->filedValue.toString()));
        //7.3存储进redis中
        redisTemplate.opsForHash().putAll(RedisConstants.LOGIN_USER_KEY + token, userMap);
        //7.4 设置有效期
        redisTemplate.expire(RedisConstants.LOGIN_USER_KEY + token, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        //通过拦截器进行token的有效期续期

        //返回token
        return Result.ok(token);
    }

    /**
     * 创建用户
     */
    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        //设置用户默认随机昵称
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        this.save(user);
        return user;
    }
}
