package com.example.dume.dataserver2.dao;

import com.example.dume.dataserver1.model.MainTable;
import com.example.dume.dataserver2.model.ChangeTable;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 16:05
 **/
public interface ChangeTableDao {
    void insertList(List<ChangeTable> list);
    List<ChangeTable> selectList();
}
