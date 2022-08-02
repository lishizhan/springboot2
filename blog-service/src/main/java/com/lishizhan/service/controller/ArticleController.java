package com.lishizhan.service.controller;

import com.lishizhan.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/2 16:44
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("articleList")
    public String ArticleListHot(){
        return articleService.articleListHot();
    }



}
