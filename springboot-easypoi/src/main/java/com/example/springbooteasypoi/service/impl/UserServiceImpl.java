package com.example.springbooteasypoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbooteasypoi.entity.Card;
import com.example.springbooteasypoi.entity.Hobby;
import com.example.springbooteasypoi.entity.User;
import com.example.springbooteasypoi.mapper.UserMapper;
import com.example.springbooteasypoi.service.CardService;
import com.example.springbooteasypoi.service.HobbyService;
import com.example.springbooteasypoi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-21 21:18:31
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private CardService cardService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public List<User> selectUserWithHobby() {
        List<User> users = this.getBaseMapper().selectList(null);
        users.forEach(user -> {
            String hobbyId = user.getHobbyId();
            String[] strings = hobbyId.split(",");
            if (!ObjectUtils.isEmpty(strings)) {
                List<Hobby> hobbies = new ArrayList<>();
                for (String id : strings) {
                    hobbies.add(hobbyService.getById(id));
                }
                user.setHobbies(hobbies);
            }
        });

        return users;
    }

    @Override
    public List<User> getUserAndCart() throws ExecutionException, InterruptedException {
        List<User> users = this.getBaseMapper().selectList(null);

        CompletableFuture<Void> futureHobby = CompletableFuture.runAsync(() -> {
            users.forEach(user -> {
                String hobbyId = user.getHobbyId();
                String[] strings = hobbyId.split(",");
                if (!ObjectUtils.isEmpty(strings)) {
                    List<Hobby> hobbies = new ArrayList<>();
                    for (String id : strings) {
                        hobbies.add(hobbyService.getById(id));
                    }
                    user.setHobbies(hobbies);
                }
            });
        }, executor);

        CompletableFuture<Void> futureCart = CompletableFuture.runAsync(() -> {
            //一对一
            for (User user : users) {
                if (!ObjectUtils.isEmpty(user.getCartId())) {
                    Card card = cardService.getById(user.getCartId());
                    user.setCard(card);
                }
            }
        }, executor);

        CompletableFuture.allOf(futureHobby, futureCart).get();
        return users;
    }
}
