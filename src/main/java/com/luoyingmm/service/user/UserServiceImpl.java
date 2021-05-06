package com.luoyingmm.service.user;

import com.luoyingmm.dao.BaseDao;
import com.luoyingmm.dao.user.UserDao;
import com.luoyingmm.dao.user.UserDaoImpl;
import com.luoyingmm.pojo.User;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getconnection();
            //业务层调用对应数据库
           user = userDao.getLoginUser(connection, userCode);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null,null);
        }
        return user;

    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("wen", "12423432");
        System.out.println(admin.getUserPassword());
    }
}
