package com.example.springbooteasypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.springbooteasypoi.entity.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author lishizhan
 * @description: Excel 导入
 * @date 2022/8/22 10:02
 */
@SpringBootTest
public class ExcelImportTest {


    /**
     * 导入excel
     */
    @Test
    public void testTest() throws Exception {
        InputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Desktop\\code\\IDEAworkspace\\springboot2\\springboot2\\springboot-easypoi\\user.xls");
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(2);
        List<UserDTO> userDTOs = ExcelImportUtil.importExcel(inputStream, UserDTO.class, params);
        userDTOs.forEach(System.out::println);

    }
}
