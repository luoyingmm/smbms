package com.luoyingmm.dao.user;

import com.luoyingmm.pojo.Role;
import com.luoyingmm.pojo.User;
import com.luoyingmm.util.Constants;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
     //检测要登录的用户
     public User getLoginUser(Connection connection, String userCode) throws SQLException;

     //修改当前用户密码
     public int updatePwd(Connection connection,int id,String password) throws SQLException;

     //查询用户总数
     public int getUserCount(Connection connection,String username,int userRole) throws SQLException;

     public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws  SQLException;


}
