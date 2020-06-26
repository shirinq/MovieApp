package com.example.movieapp.network;

import android.util.Log;

import com.example.movieapp.constans.Urls;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.ResponseMovie;
import com.example.movieapp.repository.ResponseRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import dagger.Module;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private String mBaseUrl;
    private Retrofit mRetrofit;
    private RetrofitClient mClient;

    public RetrofitModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(buildGsonConverter())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        mClient = mRetrofit.create(RetrofitClient.class);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (mBaseUrl.equals(Urls.MOVIE_BASE_URL))
            client.addInterceptor(chain -> {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "bdb2b1da07msh0d515c86e60f2c0p18778ajsn6de8ab59df9f")
                        .build();
                return chain.proceed(request);
            });
        return client.build();
    }

    private GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(Movie.class, new CustomConverter());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

    public void getTopRatedMovies(){
        mClient.getTopRatedMovies().enqueue(new Callback<List<ResponseMovie>>() {
            @Override
            public void onResponse(Call<List<ResponseMovie>> call, Response<List<ResponseMovie>> response) {
                Log.d("TAG", "onResponse: "+ response.body());
                if (response.body() == null)
                    return;
                for (ResponseMovie movie: response.body()){
                    getMovie(movie.getId());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseMovie>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+ t.getMessage() );
                EventBus.getDefault().post(false);
            }
        });
    }

    public void getMovie(String id){
        mClient.getMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie value) {
                        ResponseRepository.getInstance().addMovie(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        EventBus.getDefault().post(false);
                    }
                });
    }

}
