package com.example.patypack;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static  int postdelay_time = 2000;

    private MediaPlayer op;

    ImageView logo, logo_name;
    Animation anime_logo, anime_logo_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo_splash);
        logo_name = findViewById(R.id.logo_splash);

        anime_logo = AnimationUtils.loadAnimation(this, R.anim.logo_anime);
        anime_logo_name = AnimationUtils.loadAnimation(this, R.anim.logo_name_anime);
        op  = MediaPlayer.create(this, R.raw.pp_op);

        logo.setAnimation(anime_logo);
        op.start();
        logo_name.setAnimation(anime_logo_name);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
                overridePendingTransition(R.anim.anime_out,R.anim.anime_in);
            }
        }, postdelay_time);
    }
}