package com.luoyingmm.dao.user;

import com.luoyingmm.pojo.User;


import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
     public User getLoginUser(Connection connection, String userCode) throws SQLException;
}
