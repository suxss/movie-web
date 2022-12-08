package com.movie.web.service.jsonObjects;

public class ResultMsg {
    int error = 0;
    String msg;

    public ResultMsg(int e, String s) {
        error = e;
        msg = s;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
