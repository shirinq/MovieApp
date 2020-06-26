package com.example.movieapp.presenter.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.movieapp.R;
import com.example.movieapp.constans.Urls;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.network.RetrofitModule;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private RetrofitModule mRetrofit;

    public static Intent newIntent(Context target) {
        return new Intent(target, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mRetrofit = new RetrofitModule(Urls.MOVIE_BASE_URL);
        mRetrofit.getTopRatedMovies();
        Listener();
        onManageFragment(MovieFragment.newInstance());
    }

    private void onManageFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_main, fragment)
                .commit();
    }

    private void Listener() {
        mBinding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.movies_item:
                    onManageFragment(MovieFragment.newInstance());
                    return true;
                case R.id.favorite_item:
                    onManageFragment(FavoriteFragment.newInstance());
                    return true;
            }
            return false;
        });
    }
}
