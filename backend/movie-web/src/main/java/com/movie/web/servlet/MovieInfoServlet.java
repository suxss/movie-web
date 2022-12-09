package com.movie.web.servlet;

import com.movie.web.dto.User;
import com.movie.web.service.JSONService;
import com.movie.web.service.MovieService;
import com.movie.web.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "MovieInfoServlet", value = "/info")
public class MovieInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String mid = request.getParameter("mid");
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("user");
//        System.out.println(user.getFeature());
        UserService.record(user.getUid(), mid);
        String json = JSONService.getMovieInfoById(mid);
        PrintWriter w = response.getWriter();
        w.println(json);
    }
}
