package com.ssnail.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserJpaRepository
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/16 16:33
 * @Version 1.0
 **/
@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa,Long> {

}
