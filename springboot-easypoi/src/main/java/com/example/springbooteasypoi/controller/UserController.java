package com.example.springbooteasypoi.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbooteasypoi.entity.User;
import com.example.springbooteasypoi.service.UserService;
import com.example.springbooteasypoi.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-08-21 21:17:55
 */
@Controller
@RequestMapping
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping({"index","/","index.html"})
    public ModelAndView selectAll(Integer page,Integer size) {
        if (ObjectUtils.isEmpty(page) && ObjectUtils.isEmpty(size)){
            page=1;
            size=10;
        }
        Page<User> page1 = new Page<>(page, size);
        ModelAndView modelAndView = new ModelAndView();
        Page<User> userPage = userService.page(page1, null);
        modelAndView.addObject("data",userPage);
        modelAndView.setViewName("index");
        return modelAndView;
    }
/*
    *//**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     *//*
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.userService.getById(id));
    }

    *//**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     *//*
    @PostMapping
    public R insert(@RequestBody User user) {
        return R.ok(this.userService.save(user));
    }

    *//**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     *//*
    @PutMapping
    public R update(@RequestBody User user) {
        return R.ok(this.userService.updateById(user));
    }

    *//**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     *//*
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.userService.removeByIds(idList));
    }*/
}

