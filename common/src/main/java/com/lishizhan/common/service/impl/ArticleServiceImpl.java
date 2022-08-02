package com.lishizhan.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishizhan.common.domain.Article;
import com.lishizhan.common.service.ArticleService;
import com.lishizhan.common.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【ali_article】的数据库操作Service实现
* @createDate 2022-08-02 15:59:17
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public String articleListHot() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //构造条件
        //1，



        return null;
    }
}




