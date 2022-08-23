package com.example.dume.dataserver2.service.impl;

import com.example.dume.dataserver2.dao.ChangeTableDao;
import com.example.dume.dataserver2.model.ChangeTable;
import com.example.dume.dataserver2.service.ChangeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 16:08
 **/
@Service
public class ChangeTableServiceImpl implements ChangeTableService {
    @Autowired
    private ChangeTableDao changeTableDao;
    @Override
    public void insertList(List<ChangeTable> list) {
        changeTableDao.insertList(list);
    }

    @Override
    public List<ChangeTable> selectList() {
        return changeTableDao.selectList();
    }
}
