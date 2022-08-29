package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.AssetsRelationMapper;
import com.example.mybatisplus.entity.AssetsRelation;
import com.example.mybatisplus.vo.R;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author : lishizhan
 * @Date : 2022/8/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("fac")
@RequiredArgsConstructor
@Slf4j
public class FacController {

    private final AssetsRelationMapper assetsRelationMapper;

    @GetMapping("/relFacName")
    public R relFacName(String filter, Integer page,Integer size) {
        Long start = System.currentTimeMillis();
        String station = "1133";
        Page<AssetsRelation> page1 = new Page<>(page,size);

        List<AssetsRelation> assetsRelations = assetsRelationMapper.selectRelFac(page1,filter,station);
        assetsRelations.forEach(assetsRelation -> {
            List<String> list = assetsRelationMapper.selectRelationNameByRelationAttr(assetsRelation.getFacRelation().split(","));
            String join = StringUtils.join(Arrays.asList(list), ",").replace("[", "").replace("]", "");
            assetsRelation.setFacRelationNames(join);
        });
        long end = System.currentTimeMillis();
        log.info("耗时：{}", end - start);
        return R.ok(assetsRelations);
    }
}
