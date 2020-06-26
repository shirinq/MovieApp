package com.example.movieapp.database;


import android.content.Context;

import com.example.movieapp.model.DaoMaster;

public class MovieBaseHelper extends DaoMaster.OpenHelper {

    private static String DATABASE_NAME = "MovieBase";

    public MovieBaseHelper(Context context) {
        super(context, DATABASE_NAME);
    }
}
