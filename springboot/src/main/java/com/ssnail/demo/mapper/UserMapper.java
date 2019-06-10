package com.ssnail.demo.mapper;

import com.ssnail.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/16 15:08
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {
    /**
     * 查询用户
     * @return
     */
    List<User> queryUserList();
}
