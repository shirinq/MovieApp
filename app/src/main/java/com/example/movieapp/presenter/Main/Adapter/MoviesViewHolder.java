package com.example.movieapp.presenter.Main.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.databinding.MovieViewHolderBinding;
import com.example.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private MovieViewHolderBinding mBinding;
    private Movie mMovie;

    public MoviesViewHolder(@NonNull MovieViewHolderBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.doFavorite.setOnClickListener(view -> {
        });
    }

    public void onBind(Movie movie){
        mMovie = movie;
        mBinding.movieTitle.setText(movie.getTitle());
        mBinding.movieRate.setText(movie.getTitle());
        Picasso.get()
                .load(movie.getUrl())
                .placeholder(R.drawable.ic_action_movie)
                .error(R.drawable.ic_action_error)
                .into(mBinding.movieImage);
    }
}
