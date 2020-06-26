package com.example.movieapp.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.movieapp.R;
import com.example.movieapp.presenter.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity {

    private Snackbar mSnackbar;
    private LottieAnimationView mAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAnim = findViewById(R.id.splash);

        if (checkConnectivity()) {
            mAnim.playAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(LoginActivity.newIntent(SplashActivity.this));
                    SplashActivity.this.finish();
                }
            }, 2000);
            if (mSnackbar != null)
                mSnackbar.dismiss();

        } else showSnackBar();
    }

    private void showSnackBar() {
        mSnackbar = Snackbar.make(findViewById(R.id.splash), getString(R.string.connection_fail), Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();
        mAnim.pauseAnimation();
    }

    public boolean checkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null;
    }
}