package com.luoyingmm.dao.user;

import com.luoyingmm.dao.BaseDao;
import com.luoyingmm.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};


                rs = BaseDao.execute(connection,pstm,rs,sql,params);
                if (rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                }
                BaseDao.closeResource(null,pstm,rs);

        }

        return user;


    }

    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            Object[] params = {password,id};
            String sql = "UPDATE `smbms_user` SET `userPassword`=? WHERE `id`=? ";
            execute = BaseDao.execute(connection, pstm, sql, params);

        }
        BaseDao.closeResource(null, pstm, null);
        return execute;
    }

    public static void main(String[] args) {
        Connection connection = null;
        connection = BaseDao.getconnection();
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            int i = userDao.updatePwd(connection, 1, "1234567");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
