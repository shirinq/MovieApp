package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Movie")
public class Movie {

    @Id(autoincrement = true)
    private Long mId;

    @SerializedName("id")
    @Property(nameInDb = "MovieId")
    @Index(unique = true)
    private String id;

    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("year")
    private int year;

    @Generated(hash = 1005704000)
    public Movie(Long mId, String id, String url, String title, int year) {
        this.mId = mId;
        this.id = id;
        this.url = url;
        this.title = title;
        this.year = year;
    }

    public Movie(String id, String url, String title, int year) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.year = year;
    }

    @Generated(hash = 1263461133)
    public Movie() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
