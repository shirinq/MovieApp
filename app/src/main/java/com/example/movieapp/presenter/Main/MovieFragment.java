package com.example.movieapp.presenter.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.movieapp.R;
import com.example.movieapp.constans.Urls;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.ResponseMovie;
import com.example.movieapp.network.RetrofitModule;
import com.example.movieapp.presenter.Main.Adapter.MovieAdapter;
import com.example.movieapp.repository.ResponseRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private ProgressBar mProgressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MovieAdapter();
        ResponseRepository.getInstance().getLiveMovies().observe(this, movies -> {
            mAdapter.setMovies(movies);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mProgressBar = view.findViewById(R.id.progress);
        mRecyclerView = view.findViewById(R.id.movies_recycler);
        onRecycler();
        EventBus.getDefault().register(this);
        return view;
    }

    private void onRecycler() {
        mRecyclerView.setAdapter(mAdapter);
    }

    @Subscribe
    public void eventListener(boolean hide) {
        //if (!hide)
        mProgressBar.setVisibility(View.INVISIBLE);
        /*else
            mProgressBar.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}