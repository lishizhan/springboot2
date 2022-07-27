package com.hmdp.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexPatterns;
import com.hmdp.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service("sessionUserServiceImpl")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * session的实现方式验证码验证方式
     * */
    @Override
    public Result sendCode(String phone, HttpSession session) {
        //1，校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            //2，手机号不符合，返回错误消息
            return Result.fail("手机号格式不正确");
        }
        //3，符合生成验证码
        String code = RandomUtil.randomNumbers(6);
        //4，保存验证码到session
        session.setAttribute("code",code);
        //5，发送验证码，这个不先做
        log.debug("发送短信验证码成功-->{}",code);
        //6，返回消息
        return Result.ok();
    }
    /**
     * session的实现方式验证码验证方式
     * 短信登陆与注册一致
     * */
    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1，校验手机号
        if (RegexUtils.isPhoneInvalid(loginForm.getPhone())) {
            //1.1，手机号不符合，返回错误消息
            return Result.fail("手机号格式不正确");
        }
        //2，校验验证码
        //2.1 从session中取出验证码，注意不要硬编码，应该定义为常量
        String code = (String) session.getAttribute("code");
        String formCode = loginForm.getCode();
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
        //7，保存用户信息到session
        session.setAttribute("user",user);
        return Result.ok();
    }
    /**
     * 创建用户
     * */
    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        //设置用户默认随机昵称
        user.setNickName(USER_NICK_NAME_PREFIX+RandomUtil.randomString(10));
        this.save(user);
        return user;
    }
}
