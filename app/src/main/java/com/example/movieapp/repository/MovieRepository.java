package com.example.movieapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieapp.database.MovieBaseHelper;
import com.example.movieapp.model.DaoMaster;
import com.example.movieapp.model.DaoSession;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieDao;

import java.util.List;

public class MovieRepository {
    private static MovieRepository mInstance;
    private Context mContext;
    private MovieDao mDatabase;

    private MovieRepository(Context context){
        mContext = context.getApplicationContext();

        SQLiteDatabase db = new MovieBaseHelper(mContext).getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mDatabase = daoSession.getMovieDao();
    }

    public static MovieRepository getInstance(Context context){
        if (mInstance == null)
            mInstance = new MovieRepository(context);
        return mInstance;
    }

    public void insertMovie(Movie movie){
        mDatabase.insert(movie);
    }

    public List<Movie> getAll(){
        return mDatabase.queryBuilder().list();
    }

    public void deleteTask(Movie movie) {
        mDatabase.delete(movie);
    }
}
