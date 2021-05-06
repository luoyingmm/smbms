package com.luoyingmm.servlet.user;

import com.luoyingmm.pojo.User;
import com.luoyingmm.service.user.UserService;
import com.luoyingmm.service.user.UserServiceImpl;
import com.luoyingmm.util.Constants;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start....");

        String username = req.getParameter("userCode");
        String password = req.getParameter("userPassword");

        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        if (user != null){
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
