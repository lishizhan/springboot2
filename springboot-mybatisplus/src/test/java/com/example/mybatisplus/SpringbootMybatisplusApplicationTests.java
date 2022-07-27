package com.example.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class SpringbootMybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加操作
     */
    @Test
    public void insertTest() {
        User user = new User(null, "lisi", 23, "123123@qq.com");

        int i = userMapper.insert(user);
        System.out.println("i = " + i);
        System.out.println("user = " + user.getId());
    }

    /**
     * 逻辑删除
     */
    @Test
    public void TableLogicDeleteTest() {
        int i = userMapper.deleteById(1546400287852957698L);
        System.out.println("i = " + i);
    }

    /**
     * 批量删除
     */
    @Test
    public void batchTest(){
        int ids = userMapper.deleteBatchIds(Arrays.asList(1546698644450250754L,1546698644479610881L,1546698644504776705L));
        System.out.println("ids = " + ids);
    }
    /**
     * 查询
     * */
    @Test
    public void selectTest(){
        User user = userMapper.selectById(1546698644450250754L);
        System.out.println("user = " + user);
    }


    /**
     * 修改操作
     */
    @Test
    public void updateTest() {
        User user = new User(1546400287852957698L, "hhhh", 24, "888@qq.com", 0);
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);

    }

    /**
     * 批量插入
     */
    @Test
    public void batchInsertTest() {

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("zhans" + i);
            user.setAge(i);
            user.setEmail("123098" + i + "@qq.com");
            userMapper.insert(user);
            log.info("id---->{}", user.getId());
        }
    }

    /**
     * 分页插件
     */
    @Test
    public void pageTest() {
        //1创建page对象，传入两个参数：当前页和每页显示的记录数
        Page<User> userPage = new Page<>(1, 3);
        //2，调用分页查询的方法
        userMapper.selectPage(userPage, null);
        log.info("userPage--->{}", userPage);
        //3,通过分页对象获取分页数据
        long current = userPage.getCurrent();
        long pages = userPage.getPages();
        List<User> records = userPage.getRecords();
        long size = userPage.getSize();
        long total = userPage.getTotal();
        System.out.println("total = " + total);
        System.out.println("size = " + size);
        System.out.println("pages = " + pages);
        System.out.println("current = " + current);
        records.forEach((user)->{
            System.out.println("user = " + user);
        });
        //是否有上一页和下一页
        boolean hasNext = userPage.hasNext();
        boolean hasPrevious = userPage.hasPrevious();
        System.out.println("hasPrevious = " + hasPrevious);
        System.out.println("hasNext = " + hasNext);


    }
    @Test
    public void deleteTest(){

    }





}
