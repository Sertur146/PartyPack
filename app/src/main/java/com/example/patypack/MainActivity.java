package com.example.patypack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RusRulCall(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.ShooterActivity.class);
        startActivity(intent);
    }

    public void HeroesCall(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.HeroesActivity.class);
        startActivity(intent);
    }

    public void NeverCall(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.NeverActivity.class);
        startActivity(intent);
    }

    public void NemoyCall(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.NemoyVoprosActivity.class);
        startActivity(intent);
    }

    public void ToDCall(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.TruthOrDareActivity.class);
        startActivity(intent);
    }

    //public void TestCall(View view) {
    //    Intent intent = new Intent(getApplicationContext(), com.example.patypack.TestActivity.class);
    //    startActivity(intent);
    //}
}