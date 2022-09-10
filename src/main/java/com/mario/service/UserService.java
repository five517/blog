package com.mario.service;

import com.mario.pojo.User;

public interface UserService {

    //检查用户名与密码是否对
    User checkUser(String name,String password);
}
