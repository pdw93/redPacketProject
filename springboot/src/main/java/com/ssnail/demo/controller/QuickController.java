package com.ssnail.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName QuickController
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/11 14:15
 * @Version 1.0
 **/
@Controller
public class QuickController {
    @RequestMapping("/quick")
    @ResponseBody
    public String quick(){
        return "quick springboot!";
    }
}
