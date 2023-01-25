package com.example.patypack;

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

public class TruthOrDareActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ImageButton Infobutton, SettingsButton, Infobutton_lefty, SettingsButton_lefty;
    private TextView txtToD;
    private Button btnRandom, btnTruth, btnDare;
    private Animation button_rotation;

    List<String> Truth = new ArrayList<>();
    List<String> Dare = new ArrayList<>();

    int truth_count = 0;
    int dare_count = 0;

    boolean CompromissCheck = false, LeftyCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_or_dare);

        Infobutton = (ImageButton) findViewById(R.id.btnToD_rules);
        SettingsButton = (ImageButton) findViewById(R.id.btnToD_settings);
        //Infobutton_lefty = (ImageButton) findViewById(R.id.btnToD_rules2);
        //SettingsButton_lefty = (ImageButton) findViewById(R.id.btnToD_settings2);
        txtToD = (TextView) findViewById(R.id.ToDView);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnTruth = (Button) findViewById(R.id.btnTruth);
        btnDare = (Button) findViewById(R.id.btnDare);
        button_rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_anime);
        button_rotation.setAnimationListener(this);

        try {
            Truth = redDataT("Truth.txt");
            Dare = redDataD("Dare.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        setRandomMode();
        setCompromissMode();
        //setLeftyMode();
        super.onResume();
    }

    public List<String> redDataT(String fileName) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Truth.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String Truthstring = "";
            while ((Truthstring = reader.readLine()) != null) {
                Truth.add(Truthstring);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Truth;
    }

    public List<String> redDataD(String fileName) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Dare.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String Darestring = "";
            while ((Darestring = reader.readLine()) != null) {
                Dare.add(Darestring);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Dare;
    }

    public void TruthCall(View view) throws IOException {
        if(Truth.size() == 0){txtToD.setText("Правда или Действие");}
        else if (CompromissCheck == true) {
            int j = (int) Math.floor(Math.random() * (Truth.size() - 0) + 0);
            if (dare_count == 3){
                String TruthCall = Truth.get(j);
                txtToD.setText(TruthCall);
                btnDare.setText("Действие");
                dare_count = 0;
            }
            if (truth_count == 3){
                String DareCall = Dare.get(j);
                txtToD.setText(DareCall);
                btnTruth.setText("Правда");
                truth_count = 0;
            }
            else {
                String TruthCall = Truth.get(j);
                txtToD.setText(TruthCall);
                truth_count += 1;
                if (truth_count == 3) {
                    btnTruth.setText("Действие");
                }
            }

        }
        else {
            int j = (int) Math.floor(Math.random() * (Truth.size() - 0) + 0);
            String TruthCall = Truth.get(j);
            txtToD.setText(TruthCall);
        }
    }
    public void DareCall(View view) throws IOException {
        if(Dare.size() == 0){txtToD.setText("Правда или Действие");}
        else if (CompromissCheck == true) {
            int j = (int) Math.floor(Math.random() * (Truth.size() - 0) + 0);
            if (truth_count == 3){
                String DareCall = Dare.get(j);
                txtToD.setText(DareCall);
                btnTruth.setText("Правда");
                truth_count = 0;
            }
            if (dare_count == 3){
                String TruthCall = Truth.get(j);
                txtToD.setText(TruthCall);
                btnDare.setText("Действие");
                dare_count = 0;
            }
            else {
                String DareCall = Dare.get(j);
                txtToD.setText(DareCall);
                dare_count += 1;
                if (dare_count == 3) {
                    btnDare.setText("Правда");
                }
            }

        }
        else {
            int j = (int) Math.floor(Math.random() * (Dare.size() - 0) + 0);
            String DareCall = Dare.get(j);
            txtToD.setText(DareCall);
        }
    }

    public void RandomCall(View view) throws IOException {
        if(Dare.size() == 0 || Truth.size() == 0){txtToD.setText("Правда или Действие");}
        int k = (int) Math.round(Math.random());
        if ((k%2)==0) {
            int t = (int) Math.floor(Math.random() * (Truth.size() - 0) + 0);
            String TruthCall = Truth.get(t);
            txtToD.setText(TruthCall);
        }
        else {
            int d = (int) Math.floor(Math.random() * (Dare.size() - 0) + 0);
            String DareCall = Dare.get(d);
            txtToD.setText(DareCall);
        }
    }

    private void setRandomMode() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("tod_random", false)) {
            btnRandom.setVisibility(View.VISIBLE);
            btnRandom.setEnabled(true);
            btnTruth.setVisibility(View.INVISIBLE);
            btnTruth.setEnabled(false);
            btnDare.setVisibility(View.INVISIBLE);
            btnDare.setEnabled(false);
        } else {
            btnRandom.setVisibility(View.INVISIBLE);
            btnRandom.setEnabled(false);
            btnTruth.setVisibility(View.VISIBLE);
            btnTruth.setEnabled(true);
            btnDare.setVisibility(View.VISIBLE);
            btnDare.setEnabled(true);
        }
    }

    private void setCompromissMode() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("tod_no_compromiss", false)) {
            btnTruth.setText("Правда");
            btnDare.setText("Действие");
            CompromissCheck = true;
        } else {
            btnTruth.setText("Правда");
            btnDare.setText("Действие");
            CompromissCheck = false;
        }
    }

    //private void setLeftyMode() {
    //    if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lefty", false)) {
    //        SettingsButton_lefty.setVisibility(View.VISIBLE);
    //        Infobutton_lefty.setVisibility(View.VISIBLE);
    //        SettingsButton.setVisibility(View.INVISIBLE);
    //        Infobutton.setVisibility(View.INVISIBLE);
    //        LeftyCheck = true;
    //    } else {
    //        SettingsButton_lefty.setVisibility(View.INVISIBLE);
    //        Infobutton_lefty.setVisibility(View.INVISIBLE);
    //        SettingsButton.setVisibility(View.VISIBLE);
    //        Infobutton.setVisibility(View.VISIBLE);
    //        LeftyCheck = false;
    //    }
    //}

    public void ToDSettingsCall(View view) {
       //if (LeftyCheck == false) {
            SettingsButton.startAnimation(button_rotation);
        //}
        //else{ SettingsButton_lefty.startAnimation(button_rotation); }
    }

    public void RulesCall(View view){
        final String[] RulesArray = {"1.Сядьте в круг","2.Выберите очередность","3.Выберите правду или действие",
                "4.Случайный режим: игроки случайно получают вопрос или действие",
                "5.Режим 'Без компромиссов':",
                "5а.Если в игре случается ситуация когда идет 3 правды или 3 действия подряд," +
                        " то следующий игрок получит противоположный вариант вне зависимости от выбора",
                "6.Веселой игры", "7.Правдируй или действуй!)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(TruthOrDareActivity.this);
        builder.setTitle("Правда или Действие")
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
        //if (LeftyCheck == true) {
            //SettingsButton_lefty.setVisibility(View.INVISIBLE);
        //}
        //else{ SettingsButton.setVisibility(View.INVISIBLE); }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}