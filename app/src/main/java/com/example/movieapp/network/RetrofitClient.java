package com.example.movieapp.network;

import com.example.movieapp.constans.Urls;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.ResponseMovie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET(Urls.GET_TOP_RATED_MOVIE)
    Call<List<ResponseMovie>> getTopRatedMovies();

    @GET(Urls.GET_DETAILS)
    Observable<Movie> getMovie(@Query("tconst") String movieId);

    @GET(Urls.FIND)
    Observable<List<Movie>> findMovie(@Query("q") String movieName);
}
