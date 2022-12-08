package com.movie.web.service;

import com.movie.web.dao.MovieDao;
import com.movie.web.dto.Movie;
import com.movie.web.dto.User;
import com.movie.web.dao.UserDao;
import com.movie.web.plugins.encrypt.Encrypt;
import com.movie.web.plugins.feature.FeatureAnalyse;

import java.util.Arrays;
import java.util.Random;

public class UserService {
    public static User checkLogin(String uname, String pwd) {
        return UserDao.selectUserByNameAndPwd(uname, pwd);
    }

    public static boolean isUniqueName(String uname) {
        return UserDao.selectUserByName(uname) == null;
    }

    public static int record(String uid, String mid) {
        Movie movie = MovieDao.selectMovieByMovieId(mid);
        User user = UserDao.selectUserById(uid);
        String m_feature = movie.getFeature(), u_feature = user.getFeature();
        int n = user.getVisited_count();
        u_feature = FeatureAnalyse.addFeature(m_feature, u_feature, ((float) n) / ((float) n + 1), ((float) 1) / ((float) n + 1));
        return UserDao.updateVisitHistory(uid, u_feature);
    }

    private static String randomStr(int len) {
        String s = "0123456789abcdefg00hijklmnoprstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", r = "";
        Random random = new Random();
        int index;
        for (int i = 0; i < len; i++) {
            index = random.nextInt(s.length());
            r += s.substring(index, index + 1);
        }
        return r;
    }

    public static int createUser(String uname, String pwd) {
        String uid = randomStr(15);
        String pwd_encrypted = Encrypt.md5(pwd);
        float[] feature = new float[128];
        Arrays.fill(feature, 0f);
        String feature_str = FeatureAnalyse.array2Str(feature);
        return UserDao.insertUser(uid, uname, pwd_encrypted, feature_str);
    }
}
