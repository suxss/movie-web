package com.movie.web.service.jsonObjects;

import com.movie.web.dto.Movie;

public class IndexCard {
    String mid;
    String cover;
    float rate;

    public IndexCard(Movie m) {
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

    public String getcover() {
        return cover;
    }

    public void setcover(String cover) {
        this.cover = cover;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
