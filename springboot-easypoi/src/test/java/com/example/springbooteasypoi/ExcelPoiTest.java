package com.example.springbooteasypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.springbooteasypoi.entity.User;
import com.example.springbooteasypoi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

/**
 * @Author : lishizhan
 * @Date : 2022/8/21/0021
 * @email : 1575234570@qq.com
 * @Description :
 */
@SpringBootTest
@Slf4j
public class ExcelPoiTest {

    @Autowired
    private UserService userService;

    /**
     * 测试将User对象导出为excel
     */
    @Test
    public void testPOI() throws IOException {
        //获取数据库中的数据
        List<User> users = userService.getBaseMapper().selectList(null);
        //导出excel
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "用户基本信息"), User.class, users);
        //通过流将excel写入
        long now = System.currentTimeMillis();
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Alishiz\\Desktop\\workspace\\springboot2\\springboot-easypoi\\user_" + now + ".xls");
        sheets.write(outputStream);
        //关闭流
        outputStream.close();
        sheets.close();
    }

    /**
     * 导出集合，可以自定义get
     *
     * @throws IOException
     */
    @Test
    public void testList() throws IOException {
        //查询数据库
        List<User> users = userService.selectUserWithHobby();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "用户信息"), User.class, users);
        //通过流将excel写入
        long now = System.currentTimeMillis();
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Alishiz\\Desktop\\workspace\\springboot2\\springboot-easypoi\\user_" + now + ".xls");
        workbook.write(outputStream);
        //关闭流
        outputStream.close();
        workbook.close();
    }

    /**
     * 一对一导出
     */
    @Test
    public void testEntity() throws ExecutionException, InterruptedException, IOException {
        List<User> users = userService.getUserAndCart();
        log.info("users---{}", users);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "用户信息"), User.class, users);
        //通过流将excel写入
        long now = System.currentTimeMillis();
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Alishiz\\Desktop\\workspace\\springboot2\\springboot-easypoi\\user_" + now + ".xls");
        workbook.write(outputStream);
        //关闭流
        outputStream.close();
        workbook.close();
    }

    @Test
    public void testThread() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<User> userAndCart = userService.getUserAndCart();
        long end = System.currentTimeMillis();
        System.out.println("运行时间：" + (end - start));
    }


}
