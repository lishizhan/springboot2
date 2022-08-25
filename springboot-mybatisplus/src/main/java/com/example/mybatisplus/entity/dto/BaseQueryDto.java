package com.example.mybatisplus.entity.dto;

import lombok.Data;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/25 13:53
 */
@Data
public class BaseQueryDto {
    private Integer page=1;
    private Integer size=10;
}
