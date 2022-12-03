package com.movie.web.service;

import com.alibaba.fastjson.JSON;
import com.movie.web.dto.Movie;
import com.movie.web.plugins.filter.MovieFilter;
import com.movie.web.service.jsonObjects.IndexCard;
import com.movie.web.service.jsonObjects.MovieInfo;
import com.movie.web.service.jsonObjects.SearchCard;

import java.util.ArrayList;
import java.util.List;

public class JSONService {
        public static String search(String key) {
            List<Movie> search_result = MovieService.search(key);
            List<SearchCard> result = new ArrayList<>(search_result.size());
            for (Movie m :
                    search_result) {
                result.add(new SearchCard(m));
            }
            SearchResult sr = new SearchResult();
            sr.setResult(result);
            return JSON.toJSONString(sr);
        }

        public static String getMovieInfoById(String mid) {
            Movie m = MovieService.getMovieById(mid);
            List<MovieInfo> r = new ArrayList<MovieInfo>();
            r.add(new MovieInfo(m));
            MovieInfoResult result = new MovieInfoResult();
            result.setResult(r);
            return JSON.toJSONString(result);
        }

        public static String getMostSimilarMovies(String feature, int len, MovieFilter mf) {
            List<Movie> movies = MovieService.getMostSimilarMovies(feature, len, mf);
            List<IndexCard> result = new ArrayList<>(movies.size());
            IndexResult ir = new IndexResult();
            for (Movie m :
                    movies) {
                result.add(new IndexCard(m));
            }
            ir.setResult(result);
            return JSON.toJSONString(ir);
        }

    public static void main(String[] args) {
        String r = search("肖申克");
        System.out.println(r);
    }

    private static class SearchResult {
            List<SearchCard> result;

        public List<SearchCard> getResult() {
            return result;
        }

        public void setResult(List<SearchCard> result) {
            this.result = result;
        }
    }

    private static class IndexResult {
        List<IndexCard> result;

        public List<IndexCard> getResult() {
            return result;
        }

        public void setResult(List<IndexCard> result) {
            this.result = result;
        }
    }

    private static class MovieInfoResult {
        List<MovieInfo> result;

        public List<MovieInfo> getResult() {
            return result;
        }

        public void setResult(List<MovieInfo> result) {
            this.result = result;
        }
    }
}
