package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public Image(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
