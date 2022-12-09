package com.movie.web.filter;

import com.movie.web.dto.User;
import com.movie.web.plugins.filter.MovieFilter;
import com.movie.web.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LogFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("user") == null) {
            User default_user = UserService.defaultUser();
            MovieFilter mf = new MovieFilter(4000, 0.05);
            mf.setPath("BloomFilter_" + default_user.getUname());
            session.setAttribute("user", default_user);
            session.setAttribute("bf", mf);
        }
        chain.doFilter(request, response);
    }
}
