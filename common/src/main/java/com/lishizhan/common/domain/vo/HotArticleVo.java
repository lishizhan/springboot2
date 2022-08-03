package com.lishizhan.common.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * 返回热门文章的VO
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo implements Serializable {
    private Long id;
    /**
     * 文章标题
     * */
    private String title;
    /**
     * 访问量
     */
    private Long viewCount;
}