package com.mario.dao;

import com.mario.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    //jpa自带的方法找到用户名与密码
    User findByUsernameAndPassword(String username, String password);
}
