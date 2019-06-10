package com.ssnail.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssnail.demo.domain.User;
import com.ssnail.demo.jpa.UserJpa;
import com.ssnail.demo.jpa.UserJpaRepository;
import com.ssnail.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName UserMapperTest
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/16 16:09
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis() throws JsonProcessingException {
        String queryUserList = redisTemplate.boundValueOps("queryUserList").get();
        if (queryUserList==null){
            List<User> users = userMapper.queryUserList();
            ObjectMapper objectMapper = new ObjectMapper();
            queryUserList = objectMapper.writeValueAsString(users);
            redisTemplate.boundValueOps("queryUserList").set(queryUserList);
        }
        System.out.println(queryUserList);
    }

    @Test
    public void queryUsers(){
        List<User> users = userMapper.queryUserList();
        System.out.println(users);
    }

    @Test
    public void jpaQueryUsers(){
        List<UserJpa> all = userJpaRepository.findAll();
        System.out.println(all);
    }
}
