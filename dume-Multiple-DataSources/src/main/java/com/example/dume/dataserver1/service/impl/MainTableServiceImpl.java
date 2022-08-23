package com.example.dume.dataserver1.service.impl;

import com.example.dume.dataserver1.dao.MianTableDao;
import com.example.dume.dataserver1.model.MainTable;
import com.example.dume.dataserver1.service.MainTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 15:57
 **/
@Service
public class MainTableServiceImpl implements MainTableService {

    @Autowired
    private MianTableDao mianTableDao;
    @Override
    public List<MainTable> selectList() {
        return mianTableDao.selectList();
    }

    @Override
    public void insertList(List<MainTable> list) {
        mianTableDao.insertList(list);
    }

}
