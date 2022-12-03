package com.movie.web.service;

import com.movie.web.dao.MovieDao;
import com.movie.web.dto.Movie;
import com.movie.web.plugins.feature.FeatureAnalyse;
import com.movie.web.plugins.filter.MovieFilter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService {
    public static List<Movie> search(String key) {
        return MovieDao.searchMovies(0, 100, key);
    }

    public static @NotNull List<Movie> getMostSimilarMovies(String feature, int len, MovieFilter mf) {
        float[] f = FeatureAnalyse.str2Array(feature);
        List<Movie> Movies = MovieDao.selectMovies(0, 9000);
        for (Movie m : Movies) {
            m.setSimilarity(FeatureAnalyse.similarity(feature, m.getFeature()));
        }
        Movies.sort((o1, o2) -> {
            if (o1.getSimilarity() == o2.getSimilarity()) return 0;
            if (o1.getSimilarity() > o2.getSimilarity()) return -1;
            return 1;
        });

        int count = 0;
        List<Movie> similarMovies = new ArrayList<>(len);
        for (int i = 0; i < Movies.size(); i++) {
            if (!mf.test(Movies.get(i).getId())) {
                similarMovies.add(Movies.get(i));
                count++;
                if (count >= len) break;
            }
        }
        return similarMovies;
    }

    public static Movie getMovieById(String mid) {
        return MovieDao.selectMovieByMovieId(mid);
    }

    public static void main(String[] args) {
        MovieFilter mf = new MovieFilter(10, 0.05);
        float[] f = new float[128];
        Arrays.fill(f, 0);
        f[1] = 0.432f;
        f[4] = 0.8361f;
        String feature = FeatureAnalyse.array2Str(f);
        List<Movie> movies = getMostSimilarMovies(feature, 10, mf);
        System.out.println(movies.size());
        for (Movie m : movies) {
            System.out.println(m.getMname());
            System.out.println(m.getSimilarity());
        }
    }
}
