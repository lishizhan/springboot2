package com.example.dume.dataserver2.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dume.dataserver1.model.MainTable;
import com.example.dume.dataserver1.service.impl.MainTableServiceImpl;
import com.example.dume.dataserver2.model.ChangeTable;
import com.example.dume.dataserver2.service.impl.ChangeTableServiceImpl;
import com.example.dume.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 16:47
 **/
@RestController
@RequestMapping("/data")
public class ChangeTableController {
    private static Logger logger = LoggerFactory.getLogger(ChangeTableController.class);
    @Autowired
    private MainTableServiceImpl mainTableServiceImpl;
    @Autowired
    private ChangeTableServiceImpl changeTableServiceImpl;
    /**
     * 副数据库插入
     * @return
     */
    @PostMapping("/interface2")
    public JSONObject insertChangeTableList(@RequestBody List<ChangeTable> changeTableList){
        changeTableServiceImpl.insertList(changeTableList);
        JSONObject object = new JSONObject();
        object.put("message","副数据源插入成功！");
        return object;
    }

    /**
     * 副数据库向主数据库插入数据
     */
    @PostMapping("/interface3")
    public JSONObject getMainTableList(){
        List<ChangeTable> changeTableList = changeTableServiceImpl.selectList();
        if(!CollectionUtils.isEmpty(changeTableList)){
            List<MainTable> mainTableList = JSONArray.parseArray(JSONArray.toJSONString(changeTableList),MainTable.class);
            mainTableServiceImpl.insertList(mainTableList);
        }

        JSONObject object = new JSONObject();
        object.put("message","主数据源插入成功！");
        return object;
    }
    /**
     * 切换副数据源
     */
    @PostMapping("/interface4")
    public JSONObject reSetDataSource() throws SQLException {
        String urlnew2 = "jdbc:mysql://localhost:3306/change_db_202112?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8";
        DruidDataSource datasource = SpringUtil.getBean("db2");
        if(datasource.isInited()){
            datasource.restart();
            datasource.setUrl(urlnew2);
            datasource.init();
            logger.info("副数据源重置为："+urlnew2);
        }else{
            datasource.setUrl(urlnew2);
            datasource.init();
            logger.info("副数据源重置为："+urlnew2);
        }
        JSONObject object = new JSONObject();
        object.put("message","副数据源重置成功！");
        return object;
    }
}
