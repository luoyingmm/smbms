package com.luoyingmm.servlet.user;

import com.luoyingmm.pojo.User;
import com.luoyingmm.service.user.UserService;
import com.luoyingmm.service.user.UserServiceImpl;
import com.luoyingmm.util.Constants;
import org.junit.platform.commons.util.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null){
            this.updatePwd(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (o != null && newpassword != null){
            UserService userService = new UserServiceImpl();
            System.out.println(((User) o).getId() + "   " + newpassword);
            flag = userService.updatePwd(((User) o).getId(), newpassword);

            if (flag){
                req.setAttribute("message","密码修改成功，请退出，使用新密码登录");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","密码修改失败");
            }
        }else {
            req.setAttribute("message","新密码有问题");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
