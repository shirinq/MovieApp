package com.example.movieapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ResponseRepository {
    private static ResponseRepository mInstance;
    private MutableLiveData<List<Movie>> mLiveMovies;

    private ResponseRepository(){
        mLiveMovies = new MutableLiveData<>();
        mLiveMovies.setValue(new ArrayList<>());
    }

    public static ResponseRepository getInstance(){
        if (mInstance == null)
            mInstance = new ResponseRepository();
        return mInstance;
    }

    public void addMovie(Movie movie){
        mLiveMovies.getValue().add(movie);
    }

    public LiveData<List<Movie>> getLiveMovies(){
        return mLiveMovies;
    }

    public void setLiveMovies(List<Movie> movies){
        mLiveMovies.setValue(movies);
    }
}
