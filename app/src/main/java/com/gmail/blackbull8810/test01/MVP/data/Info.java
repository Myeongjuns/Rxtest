package com.gmail.blackbull8810.test01.MVP.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {

    @SerializedName("list")
    public List<InfoList> data = null;

    public class InfoList {
        @SerializedName("id")
        public int id;
        @SerializedName("pw")
        public int pw;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPw() {
            return pw;
        }

        public void setPw(int pw) {
            this.pw = pw;
        }

        @Override
        public String toString() {
            return "InfoList{" +
                    "id=" + id +
                    ", pw=" + pw +
                    '}';
        }
    }
}
