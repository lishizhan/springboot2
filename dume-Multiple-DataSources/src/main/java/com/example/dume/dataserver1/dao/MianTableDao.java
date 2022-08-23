package com.example.dume.dataserver1.dao;

import com.example.dume.dataserver1.model.MainTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 15:53
 **/
@Mapper
public interface  MianTableDao {
    List<MainTable> selectList();
    void insertList (List<MainTable> list);
}
