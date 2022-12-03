package com.movie.web.service.jsonObjects;

import com.movie.web.dto.Movie;

public class SearchCard {
    String mid;
    String title;
    String cover;
    float rate;
    String director;
    String actors;
    String type;

    public SearchCard(Movie m) {
        this.mid = m.getId();
        this.title = m.getMname();
        this.cover = m.getCover();
        this.rate = m.getRate();
        this.director = m.getDirector();
        this.actors = m.getActors();
        this.type = m.getType();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
