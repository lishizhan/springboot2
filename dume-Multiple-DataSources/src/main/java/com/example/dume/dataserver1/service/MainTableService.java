package com.example.dume.dataserver1.service;

import com.example.dume.dataserver1.model.MainTable;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 15:56
 **/
public interface MainTableService {
    List<MainTable> selectList();
    void insertList (List<MainTable> list);
}
