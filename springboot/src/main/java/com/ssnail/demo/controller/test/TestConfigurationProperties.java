package com.ssnail.demo.controller.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName TestConfigurationProperties
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/16 14:33
 * @Version 1.0
 **/
@Controller
@ConfigurationProperties(prefix = "test")
public class TestConfigurationProperties {
    private String prefix;

    @RequestMapping("/queryPrefix")
    @ResponseBody
    public String queryPrefix(){
        return prefix;
    }
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
