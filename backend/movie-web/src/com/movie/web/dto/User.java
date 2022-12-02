package com.movie.web.dto;

public class User {
    private String uid;
    private String uname;
    private String pwd;
    private String feature;
    private int visited_count;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getVisited_count() {
        return visited_count;
    }

    public void setVisited_count(int visited_count) {
        this.visited_count = visited_count;
    }
}
