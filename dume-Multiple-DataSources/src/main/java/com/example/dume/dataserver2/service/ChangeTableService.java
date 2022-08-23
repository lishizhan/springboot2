package com.example.dume.dataserver2.service;

import com.example.dume.dataserver2.model.ChangeTable;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 16:06
 **/
public interface ChangeTableService {
    void insertList(List<ChangeTable> list);
    List<ChangeTable> selectList();
}
