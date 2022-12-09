package com.movie.web.service.jsonObjects;

import com.movie.web.dto.Movie;

public class IndexCard {
    String mid;
    String cover;
    String title;
    float rate;

    public IndexCard(Movie m) {
        this.title = m.getMname();
        this.mid = m.getId();
        this.cover = m.getCover();
        this.rate = m.getRate();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
