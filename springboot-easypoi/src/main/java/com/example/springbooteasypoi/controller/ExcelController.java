package com.example.springbooteasypoi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/23 16:08
 */
@Controller
@RequestMapping("excel")
@Slf4j
public class ExcelController {

    /**
     * 导入excel
     */
    @PostMapping("importExcel")
    public String importExcel(MultipartFile filename){
        String filename1 = filename.getOriginalFilename();
        log.info("文件名---》{}",filename1);
        return "index";
    }
}
