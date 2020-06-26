package com.example.movieapp.network;

import com.example.movieapp.model.Movie;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CustomConverter implements JsonDeserializer<Movie> {
    @Override
    public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        String url = new String();
        String id;
        String name ;
        int year;
        if (jObject.has("image"))
            url = jObject.getAsJsonObject("image").getAsJsonObject("url").getAsString();
        id = jObject.getAsJsonObject("id").getAsString();
        name = jObject.getAsJsonObject("title").getAsString();
        year = jObject.getAsJsonObject("year").getAsInt();

        return new Movie(id, url, name, year);
    }
}
