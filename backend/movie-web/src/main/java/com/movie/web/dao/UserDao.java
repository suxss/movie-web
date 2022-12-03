package com.movie.web.dao;

import com.movie.web.dto.User;
import com.movie.web.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    public static User selectUserByName(String uname) {
        User user = null;
        try {
            String sql = "select uid, uname, pwd, feature from user where uname = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), uname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User selectUserById(String uid) {
        User user = null;
        try {
            String sql = "select uid, uname, pwd, feature from user where uid = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User selectUserByNameAndId(String uname, String pwd) {
        User user = null;
        try {
            String sql = "select uid, uname, pwd, feature from user where uname = ? and pwd = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), uname, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
