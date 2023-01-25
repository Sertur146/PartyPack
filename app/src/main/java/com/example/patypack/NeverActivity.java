package com.example.patypack;

import static com.example.patypack.R.id.txtNever;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NeverActivity extends AppCompatActivity implements Animation.AnimationListener{

    private TextView textView;
    private ImageButton Infobutton, SettingsButton, Infobutton_lefty, SettingsButton_lefty;
    List<String> Never = new ArrayList<>();
    ImageView arrow_part_1, arrow_part_2,arrow_part_3,arrow_part_4,arrow_part_5;
    private Animation blik_anime_1, blik_anime_2, blik_anime_3, blik_anime_4, blik_anime_5,
            button_rotation;
    private MediaPlayer arrow_sound;
    String Nevercall;
    boolean btnNeverClickCheck, btnSettingsClickCheck, LeftyCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_never);

        textView = (TextView) findViewById(txtNever);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Infobutton = (ImageButton) findViewById(R.id.btnNever_rules);
        SettingsButton = (ImageButton) findViewById(R.id.btnNever_settings);
        //Infobutton_lefty = (ImageButton) findViewById(R.id.btnNever_rules2);
        //SettingsButton_lefty = (ImageButton) findViewById(R.id.btnNever_settings2);
        arrow_part_1 = (ImageView) findViewById(R.id.arrow_part_1);
        arrow_part_2 = (ImageView) findViewById(R.id.arrow_part_2);
        arrow_part_3 = (ImageView) findViewById(R.id.arrow_part_3);
        arrow_part_4 = (ImageView) findViewById(R.id.arrow_part_4);
        arrow_part_5 = (ImageView) findViewById(R.id.arrow_part_5);
        blik_anime_1 = AnimationUtils.loadAnimation(this, R.anim.blik);
        blik_anime_2 = AnimationUtils.loadAnimation(this, R.anim.blik);
        blik_anime_3 = AnimationUtils.loadAnimation(this, R.anim.blik);
        blik_anime_4 = AnimationUtils.loadAnimation(this, R.anim.blik);
        blik_anime_5 = AnimationUtils.loadAnimation(this, R.anim.blik);
        blik_anime_5.setAnimationListener(this);
        arrow_sound = MediaPlayer.create(this, R.raw.arrow_never);
        button_rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_anime);
        button_rotation.setAnimationListener(this);

        try {
            Never = redDataN("Never.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        //setLeftyMode();
        super.onResume();
    }

    public List<String> redDataN(String fileNameN) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Never.txt");
        try {
            BufferedReader readerN = new BufferedReader(new InputStreamReader(fis));
            String INever = "";
            while ((INever = readerN.readLine()) != null) {
                Never.add(INever);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Never;
    }

    public void INeverCall(View view) throws IOException {
        if(Never.size() == 0){textView.setText("...");}
        else {
            textView.setText("...");
            arrow_sound.seekTo(0);
            int j = (int) Math.floor(Math.random() * (Never.size() - 0) + 0);
            Nevercall = Never.get(j);
            for (int k = 0; k < 5; k++) {
                if (k == 0) {arrow_part_1.startAnimation(blik_anime_1);arrow_sound.start();}
                if (k == 1) {blik_anime_2.setStartOffset(350);arrow_part_2.startAnimation(blik_anime_2);}
                if (k == 2) {blik_anime_3.setStartOffset(700);arrow_part_3.startAnimation(blik_anime_3);}
                if (k == 3) {blik_anime_4.setStartOffset(1050);arrow_part_4.startAnimation(blik_anime_4);}
                if (k == 4) {blik_anime_5.setStartOffset(1400);arrow_part_5.startAnimation(blik_anime_5);}
            }
            btnNeverClickCheck = true;
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

    public void NeverSettingsCall(View view) {
        SettingsButton.startAnimation(button_rotation);
        btnSettingsClickCheck = true;
    }

    public void RulesCall(View view){
        final String[] RulesArray = {"1.Сядьте в круг","2.Выберите очередность","3.Сделайте заявление",
                "4.Если кто-либо из игроков, совершал названное действие он пьет(либо же загибает палец)",
                "5.Передайте ход","6.Веселой игры","Удивите себя и друзей!)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(NeverActivity.this);
        builder.setTitle("Я никогда не...")
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
        if(btnNeverClickCheck == true){
            textView.setText(Nevercall);
            btnNeverClickCheck = false;
        }
        if (btnSettingsClickCheck == true){
            //if (LeftyCheck == true) {
            //    SettingsButton.setVisibility(View.INVISIBLE);
            //}
            //else{ SettingsButton_lefty.setVisibility(View.INVISIBLE); }
            Intent intent = new Intent(getApplicationContext(), com.example.patypack.SettingsActivity.class);
            startActivity(intent);
            btnSettingsClickCheck = false;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}