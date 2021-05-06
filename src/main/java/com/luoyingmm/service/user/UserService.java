package com.luoyingmm.service.user;

import com.luoyingmm.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);
}
