package org.example.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/3 10:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String category;
    private Integer score;
    private String intro;
}
