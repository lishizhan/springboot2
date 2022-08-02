package com.lishizhan.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ali_article
 */
@Data
public class Article implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     *
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章类型：1文章、2草稿
     */
    private String type;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 所属分类ID
     */
    private Long categoryId;

    /**
     * 缩略图
     */
    private String thumbnall;

    /**
     * 是否顶置
     */
    private String isTop;

    /**
     * 状态0已发布，1草稿
     */
    private String status;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 
     */
    private String isComment;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 0表示未删除
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}