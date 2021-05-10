package com.luoyingmm.service.user;

import com.luoyingmm.pojo.Role;
import com.luoyingmm.pojo.User;

import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);

    //密码修改
    public boolean updatePwd(int id,String pwd);

    public int getUserCount(String username,int userRole);

    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);


}
