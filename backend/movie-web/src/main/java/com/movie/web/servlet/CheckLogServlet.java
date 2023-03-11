package com.movie.web.servlet;

import com.alibaba.fastjson.JSON;
import com.movie.web.dto.User;
import com.movie.web.service.jsonObjects.ResultMsg;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "CheckLogServlet", value = "/check")
public class CheckLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String name = "";
        int e = -1;
        if (u != null && !u.getUid().equals("tdgf9rLQMbTeVrh")) {
            e = 0;
            name = u.getUname();
        }
        ResultMsg r = new ResultMsg(e, name);
        String j = JSON.toJSONString(r);
        PrintWriter writer = response.getWriter();
        writer.println(j);
    }
}
