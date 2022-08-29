package com.example.mybatisplus.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.AssetsRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.mybatisplus.entity.AssetsRelation
 */
public interface AssetsRelationMapper extends BaseMapper<AssetsRelation> {

    List<AssetsRelation> selectRelFac(Page<AssetsRelation> page1, @Param("filter") String filter, @Param("station") String station);

    List<String> selectRelationNameByRelationAttr(String[] relation);
}




