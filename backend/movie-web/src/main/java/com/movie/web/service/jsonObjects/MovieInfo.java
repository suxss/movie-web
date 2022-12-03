package com.movie.web.service.jsonObjects;

import com.movie.web.dto.Movie;
import com.movie.web.service.MovieService;

import java.util.ArrayList;
import java.util.List;

public class MovieInfo {
    String id;
    String IMDb;
    float rate;
    int rate_num;
    String content;
    String actors;
    String directors;
    String type;
    float one_star;
    float two_star;
    float three_star;
    float four_star;
    float five_star;
    String title;
    String cover;
    List<IndexCard> relative = new ArrayList<>(4);

    public MovieInfo(Movie m) {
        this.id = m.getId();
        this.IMDb = m.getImdb();
        this.rate = m.getRate();
        this.rate_num = m.getRate_num();
        this.content = m.getContent();
        this.actors = m.getActors();
        this.directors = m.getDirector();
        this.type = m.getType();
        this.one_star = m.getOne_star();
        this.two_star = m.getTwo_star();
        this.three_star = m.getThree_star();
        this.four_star = m.getFour_star();
        this.five_star = m.getFive_star();
        this.title = m.getMname();
        this.cover = m.getCover();
        this.relative.add(new IndexCard(MovieService.getMovieById(m.getS1_mid())));
        this.relative.add(new IndexCard(MovieService.getMovieById(m.getS2_mid())));
        this.relative.add(new IndexCard(MovieService.getMovieById(m.getS3_mid())));
        this.relative.add(new IndexCard(MovieService.getMovieById(m.getS4_mid())));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIMDb() {
        return IMDb;
    }

    public void setIMDb(String IMDb) {
        this.IMDb = IMDb;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getRate_num() {
        return rate_num;
    }

    public void setRate_num(int rate_num) {
        this.rate_num = rate_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getOne_star() {
        return one_star;
    }

    public void setOne_star(float one_star) {
        this.one_star = one_star;
    }

    public float getTwo_star() {
        return two_star;
    }

    public void setTwo_star(float two_star) {
        this.two_star = two_star;
    }

    public float getThree_star() {
        return three_star;
    }

    public void setThree_star(float three_star) {
        this.three_star = three_star;
    }

    public float getFour_star() {
        return four_star;
    }

    public void setFour_star(float four_star) {
        this.four_star = four_star;
    }

    public float getFive_star() {
        return five_star;
    }

    public void setFive_star(float five_star) {
        this.five_star = five_star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<IndexCard> getRelative() {
        return relative;
    }

    public void setRelative(List<IndexCard> relative) {
        this.relative = relative;
    }
}
