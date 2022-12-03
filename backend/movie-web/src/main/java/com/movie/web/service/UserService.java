package com.movie.web.service;

import com.movie.web.dto.User;
import com.movie.web.dao.UserDao;

public class UserService {
    public User checkLogin(String uname, String pwd) {
        return UserDao.selectUserByNameAndId(uname, pwd);
    }

    public boolean isUniqueName(String uname) {
        return UserDao.selectUserByName(uname) == null;
    }

    public
}
