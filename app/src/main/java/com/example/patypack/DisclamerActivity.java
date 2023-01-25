package com.example.patypack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DisclamerActivity extends AppCompatActivity {
    private static  int postdelay_time = 2000;

    ImageView disclamer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclamer);

        disclamer = findViewById(R.id.disclamer);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(DisclamerActivity.this,SplashScreenActivity.class));
                finish();
            }
        }, postdelay_time);
    }
}