package org.example.mvc3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC 支持Ant风格的路径
 * Ant====模糊匹配
 ？：表示任意的单个字符
 *：表示任意的0个或多个字符
 **：表示任意的一层或多层目录
*/
//注意：在使用**时，只能使用/**/xxx的方式
@Controller
@Slf4j
public class AntController {

    /**
     *           *：表示任意的0个或多个字符
     * @return
     */
    @RequestMapping("/*.action")
    @ResponseBody
    public String antTest1(){
        log.info("success");
        return "success";
    }

    /**
     *           ？：表示任意的单个字符
     * @return
     */
    @RequestMapping("/test?.action")
    @ResponseBody
    public String antTest2(){
        log.info("success");
        return "success";
    }
    /**
     *           **：表示任意的一层或多层目录
     *           注意：在使用**时，只能使用/ ** / xxx的方式
     * @return
     */
    @RequestMapping("/**/test.action")
    @ResponseBody
    public String antTest3(){
        log.info("success");
        return "success";
    }


}
