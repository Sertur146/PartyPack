package com.example.patypack;

import static com.example.patypack.R.id.HeroView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HeroesActivity extends AppCompatActivity implements Animation.AnimationListener {

    private TextView textView;
    private ImageButton Infobutton, SettingsButton, Infobutton_lefty, SettingsButton_lefty;
    private Button btnHero;
    private Animation button_rotation;
    List<String> Heroes = new ArrayList<>();
    List<String> SuperHeroes = new ArrayList<>();
    List<String> SuperHeroines = new ArrayList<>();
    List<String> Powers = new ArrayList<>();
    List<String> Powers_f = new ArrayList<>();

    boolean SuperheroCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        textView = (TextView) findViewById(HeroView);
        Infobutton = (ImageButton) findViewById(R.id.btnHeroes_rules);
        SettingsButton = (ImageButton) findViewById(R.id.btnHeroes_settings);
        //Infobutton_lefty = (ImageButton) findViewById(R.id.btnHeroes_rules2);
        //SettingsButton_lefty = (ImageButton) findViewById(R.id.btnHeroes_settings2);
        btnHero = (Button) findViewById(R.id.btnHeroesCall);

        button_rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_anime);
        button_rotation.setAnimationListener(this);

        try {
            Heroes = redDataH("Heroes.txt");
            Powers = redDataP("Powers.txt");
            SuperHeroes = redDataS("SuperHeroes.txt");
            Powers_f = redDataPf("Powers_f.txt");
            SuperHeroines = redDataSf("SuperHeroines.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        setSuperHeroMode();
        //setLeftyMode();
        super.onResume();
    }

    public List<String> redDataH(String fileNameH) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Heroes.txt");
        try {
            BufferedReader readerH = new BufferedReader(new InputStreamReader(fis));
            String Hero = "";
            while ((Hero = readerH.readLine()) != null) {
                Heroes.add(Hero);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Heroes;
    }

    public List<String> redDataS(String fileNameS) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("SuperHeroes.txt");
        try {
            BufferedReader readerS = new BufferedReader(new InputStreamReader(fis));
            String SuperHero = "";
            while ((SuperHero = readerS.readLine()) != null) {
                SuperHeroes.add(SuperHero);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SuperHeroes;
    }

    public List<String> redDataSf(String fileNameS) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("SuperHeroines.txt");
        try {
            BufferedReader readerSf = new BufferedReader(new InputStreamReader(fis));
            String SuperHeroine = "";
            while ((SuperHeroine = readerSf.readLine()) != null) {
                SuperHeroines.add(SuperHeroine);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SuperHeroines;
    }

    public List<String> redDataP(String fileNameP) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis1 = assManager.open("Powers.txt");
        try {
            BufferedReader readerP = new BufferedReader(new InputStreamReader(fis1));
            String Power = "";
            while ((Power = readerP.readLine()) != null) {
                Powers.add(Power);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Powers;
    }

    public List<String> redDataPf(String fileNameP) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis1 = assManager.open("Powers_f.txt");
        try {
            BufferedReader readerPf = new BufferedReader(new InputStreamReader(fis1));
            String Power_f = "";
            while ((Power_f = readerPf.readLine()) != null) {
                Powers_f.add(Power_f);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Powers_f;
    }

    public void GenerateSuperHeroCall() {
        if(SuperHeroes.size() == 0){textView.setText("Имя Игрока");}
        if(SuperHeroines.size() == 0){textView.setText("Имя Игрока");}
        int k = (int) Math.round(Math.random());
        if ((k%2)==0) {
            int l = (int) Math.floor(Math.random() * (Powers_f.size() - 0) + 0);
            int m = (int) Math.floor(Math.random() * (SuperHeroines.size() - 0) + 0);
            String SuperHeroinecall = SuperHeroines.get(m);
            String Powerfcall = Powers_f.get(l);
            textView.setText(SuperHeroinecall + ", " + Powerfcall);
        }
        else {
            int i = (int) Math.floor(Math.random() * (Powers.size() - 0) + 0);
            int j = (int) Math.floor(Math.random() * (SuperHeroes.size() - 0) + 0);
            String SuperHerocall = SuperHeroes.get(j);
            String Powercall = Powers.get(i);
            textView.setText(SuperHerocall + ", " + Powercall);
        }
    }

    public void GenerateHeroCall(View view) throws IOException {
        if(Heroes.size() == 0){textView.setText(" ");}
        if (SuperheroCheck == true){ GenerateSuperHeroCall(); }
        else {
            int j = (int) Math.floor(Math.random() * (Heroes.size() - 0) + 0);
            String Herocall = Heroes.get(j);
            textView.setText(Herocall);
        }
    }

    private void setSuperHeroMode() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("superhero", false)) {
            btnHero.setText("Вызвать Супергероя");
            SuperheroCheck = true;
        } else {
            btnHero.setText("Вызвать Героя");
            SuperheroCheck = false;
        }
    }

    //private void setLeftyMode() {
    //    if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lefty", false)) {
    //        SettingsButton_lefty.setVisibility(View.VISIBLE);
    //        Infobutton_lefty.setVisibility(View.VISIBLE);
    //        SettingsButton.setVisibility(View.INVISIBLE);
    //        Infobutton.setVisibility(View.INVISIBLE);
    //    } else {
    //        SettingsButton_lefty.setVisibility(View.INVISIBLE);
    //        Infobutton_lefty.setVisibility(View.INVISIBLE);
    //        SettingsButton.setVisibility(View.VISIBLE);
    //        Infobutton.setVisibility(View.VISIBLE);
    //    }
    //}

    public void HeroesSettingsCall(View view) {
        SettingsButton.startAnimation(button_rotation);
    }

    public void RulesCall(View view){
        final String[] RulesArray = {"1.Выберите игрока, который будет угадывать","2.Придумайте ситуацию",
                "3.Выберите уровень сложности(герой/супергой)",
                "4.Получите и покажите персонажа, остальным игрокам (кроме угадывающего)",
                "5.Веселой игры","Этой вечеринке нужны свои герои!"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HeroesActivity.this);
        builder.setTitle("Герои по вызову")
                .setItems(RulesArray,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Infobutton.setImageResource(R.drawable.ic_info_logo);
                        Toast.makeText(getApplicationContext(),
                                "" + RulesArray[which],
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Infobutton.setImageResource(R.drawable.ic_info_logo);
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
        Infobutton.setImageResource(R.drawable.ic_info_red_btn);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(getApplicationContext(), com.example.patypack.SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}