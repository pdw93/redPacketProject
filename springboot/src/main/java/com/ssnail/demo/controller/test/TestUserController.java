package com.ssnail.demo.controller.test;

import com.ssnail.demo.domain.User;
import com.ssnail.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName TestUserController
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/16 15:10
 * @Version 1.0
 **/
@Controller
public class TestUserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/queryUsers", method = {RequestMethod.GET})
    @ResponseBody
    public List<User> queryUsers() {
        List<User> users = userMapper.queryUserList();
        return users;
    }
}
