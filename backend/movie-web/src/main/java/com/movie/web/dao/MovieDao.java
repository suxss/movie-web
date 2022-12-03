package com.movie.web.dao;

import com.movie.web.dto.Movie;
import com.movie.web.dto.MovieBrief;
import com.movie.web.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    public static Movie selectMovieByMovieId(String movieId){
        Movie movie = null;
        try {
            String sql = "SELECT mid id, mname, pic cover, imdb, type, content, director, actors, rate, rate_num, feature, one_star, two_star, three_star, four_star, five_star, s1_mid, s2_mid, s3_mid, s4_mid from movie where mid = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            movie = queryRunner.query(sql, new BeanHandler<Movie>(Movie.class), movieId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static MovieBrief selectBriefMovieByMovieId(String movieId){
        MovieBrief movie = null;
        try {
            String sql = "SELECT mid id, mname, pic cover from movie where mid = ?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            movie = queryRunner.query(sql, new BeanHandler<>(MovieBrief.class), movieId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static List<Movie> selectMovies(int start, int limit){
        List<Movie> moviesList = new ArrayList<Movie>(limit);
        try {
            String sql = "SELECT mid id, mname, pic cover, imdb, type, content, director, actors, rate, rate_num, feature, one_star, two_star, three_star, four_star, five_star, s1_mid, s2_mid, s3_mid, s4_mid FROM movie limit ?,? ";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            moviesList = queryRunner.query(sql, new BeanListHandler<>(Movie.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public static List<Movie> selectSimilarMovies(int start, int limit, String feature){
        List<Movie> moviesList = new ArrayList<Movie>(limit);
        try {
            String sql = "SELECT mid id, mname, pic cover, imdb, type, content, director, actors, rate, rate_num, feature, one_star, two_star, three_star, four_star, five_star, s1_mid, s2_mid, s3_mid, s4_mid FROM movie order by func_similarity(feature, ?) limit ?,?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            moviesList = (List<Movie>) queryRunner.query(sql, new BeanListHandler<Movie>(Movie.class), feature, start, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public static List<Movie> searchMovies(int start, int limit, String keyword){
        List<Movie> moviesList = new ArrayList<Movie>(limit);
        try {
            String sql = "SELECT mid id, mname, pic cover, type, director, actors, rate, feature FROM movie where match(mname) against (? in boolean mode ) limit ?,?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            moviesList = (List<Movie>) queryRunner.query(sql, new BeanListHandler<Movie>(Movie.class), keyword, start, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moviesList;
    }


}
