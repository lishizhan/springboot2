package com.example.springbooteasypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelImportEntity;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.example.springbooteasypoi.entity.Card;
import com.example.springbooteasypoi.entity.dto.CardDTO;
import com.example.springbooteasypoi.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/23 16:08
 */
@Controller
@RequestMapping("excel")
@Slf4j
@RequiredArgsConstructor
public class ExcelController {

    private final CardService cardService;

    /**
     * 导入excel
     */
    @PostMapping("importExcel")
    public String importExcel(MultipartFile filename) throws Exception {
        String filename1 = filename.getOriginalFilename();
        log.info("文件名---》{}",filename1);
        //excel导入
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<CardDTO> cardDTOS= ExcelImportUtil.importExcel(filename.getInputStream(), CardDTO.class, params);
        cardDTOS.forEach(System.out::println);
        List<Card> cardList = BeanUtil.copyToList(cardDTOS, Card.class);
        cardService.saveBatch(cardList);
        return "redirect:http://localhost:9091/index.html";
    }

    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        //查询数据库
        List<Card> cards = cardService.getBaseMapper().selectList(null);
        log.info("数据库中需要导出的数据为：{}",cards);

        //生成excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户身份信息列表", "用户身份信息"), Card.class, cards);
        //设置下载请求头
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("用户身份信息"+UUID.randomUUID().toString().substring(10)+".xls","UTF-8"));

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    }

}
