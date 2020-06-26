package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseMovie {

    @SerializedName("id")
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id.substring(7, id.length()-1);
    }

    public ResponseMovie(String id) {
        setId(id);
    }
}
