package com.movie.web.servlet;

import com.movie.web.dto.User;
import com.movie.web.plugins.filter.MovieFilter;
import com.movie.web.service.JSONService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MovieListServlet", value = "/movielist")
public class MovieListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        MovieFilter mf = (MovieFilter) session.getAttribute("bf");
        String json = JSONService.getMostSimilarMovies(user.getFeature(), 20, mf);
        PrintWriter w = response.getWriter();
        w.println(json);
    }
}
