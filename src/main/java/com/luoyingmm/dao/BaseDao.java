package com.luoyingmm.dao;



import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;

import java.sql.*;
import java.util.Properties;


public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static {
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public static Connection getconnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
             connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    public static int execute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;

    }

    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        Boolean flag = true;

        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            flag = false;
        }

        if (preparedStatement != null){
            try {
                preparedStatement.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            flag = false;
        }

        if (resultSet != null){
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            flag = false;
        }

        return flag;
    }

}
