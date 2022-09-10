package com.mario.service;

import com.mario.dao.UserRepository;
import com.mario.pojo.User;
import com.mario.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    //引入Dao
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        //找到用户名与密码对应的user
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
