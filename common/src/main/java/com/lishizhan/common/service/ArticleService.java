package com.lishizhan.common.service;

import com.lishizhan.common.domain.ResponseResult;
import com.lishizhan.common.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Admin
* @description 针对表【ali_article】的数据库操作Service
* @createDate 2022-08-02 15:59:17
*/
public interface ArticleService extends IService<Article> {

    /**
     * 查询当前热度最高的博客
     * */
    ResponseResult articleListHot();

}
