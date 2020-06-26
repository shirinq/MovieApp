package com.example.movieapp.presenter.Main.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.databinding.MovieViewHolderBinding;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.ResponseMovie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();

    public void setMovies(List<Movie> movies){
        this.mMovies = movies;
    }
    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder (MovieViewHolderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.onBind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
