package com.lishizhan.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishizhan.common.domain.ResponseResult;
import com.lishizhan.common.domain.entity.Article;
import com.lishizhan.common.domain.vo.HotArticleVo;
import com.lishizhan.common.service.ArticleService;
import com.lishizhan.common.mapper.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询热门文章
     * */
    @Override
    public ResponseResult articleListHot() {
        //查询热门文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //构造条件
        //1，必须是正式发表的文章
        queryWrapper.eq(Article::getStatus,0);
        //2，按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //3，最多查10条
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        //获取查询列表
        List<Article> articleList = page.getRecords();
        //封装VO
        List<HotArticleVo> hotArticleVoList = new ArrayList<>();
        //bean属性拷贝
        articleList.forEach(article -> {
            HotArticleVo hotArticleVo = new HotArticleVo();
            BeanUtils.copyProperties(article,hotArticleVo);
            hotArticleVoList.add(hotArticleVo);
        });
        return ResponseResult.okResult(hotArticleVoList);
    }
}




