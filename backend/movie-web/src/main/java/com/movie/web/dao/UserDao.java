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
            String sql = "select uid, uname, pwd, feature, visited_count from user where uname = ?";
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
            String sql = "select uid, uname, pwd, feature, visited_count from user where uid = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User selectUserByNameAndPwd(String uname, String pwd) {
        User user = null;
        try {
            String sql = "select uid, uname, pwd, feature, visited_count from user where uname = ? and pwd = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), uname, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int updateVisitHistory(String uid, String feature) {
        int r=0;
        try {
            String sql = "update user set feature = ?, visited_count = visited_count + 1 where uid = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            r = queryRunner.update(sql, feature, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static int insertUser(String uid, String uname, String feature, String pwd) {
        int visited_count=0, r=0;
        try {
            String sql = "insert into user(uid, uname, pwd, feature) values (?, ?, ?, ?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            r = queryRunner.update(sql, uid, uname, feature, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
