package com.movie.web.servlet;

import com.alibaba.fastjson.JSON;
import com.movie.web.dto.User;
import com.movie.web.plugins.filter.MovieFilter;
import com.movie.web.plugins.filter.proto.BloomFilterProtos;
import com.movie.web.service.JSONService;
import com.movie.web.service.UserService;
import com.movie.web.service.jsonObjects.ResultMsg;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 登录
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        User u = UserService.checkLogin(username, pwd);
        HttpSession session = request.getSession();
        String msg = "登陆失败";
        int e = 1;
        if (u != null) {
            session.setAttribute("user", u);
            MovieFilter movieFilter = new MovieFilter("BloomFilter_" + u.getUid());
            session.setAttribute("bf", movieFilter);
            msg = "登陆成功";
            e = 0;
        }
        ResultMsg r = new ResultMsg(e, msg);
        String j = JSON.toJSONString(r);
        PrintWriter writer = response.getWriter();
        writer.println(j);
    }
}

