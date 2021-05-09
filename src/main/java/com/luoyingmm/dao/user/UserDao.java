package com.luoyingmm.dao.user;

import com.luoyingmm.pojo.User;
import com.luoyingmm.util.Constants;


import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
     //检测要登录的用户
     public User getLoginUser(Connection connection, String userCode) throws SQLException;

     //修改当前用户密码
     public int updatePwd(Connection connection,int id,String password) throws SQLException;
}
