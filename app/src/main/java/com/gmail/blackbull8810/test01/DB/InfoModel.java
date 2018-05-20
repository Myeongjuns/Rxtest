package com.gmail.blackbull8810.test01.DB;

public class InfoModel {

    String id;
    String pw;

    public InfoModel(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {

        return id;
    }

    public String getPw() {
        return pw;
    }

    @Override
    public String toString() {
        return "InfoModel{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
