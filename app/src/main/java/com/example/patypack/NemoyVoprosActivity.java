package com.example.patypack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
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

public class NemoyVoprosActivity extends AppCompatActivity implements Animation.AnimationListener {

    private TextView TimeView, QuestAnswView;
    private Button Pause, Answer;
    private ImageButton Infobutton, SettingsButton, Infobutton_lefty, SettingsButton_lefty;
    private Animation button_rotation;
    List<String> Questions = new ArrayList<>();
    List<String> Answers = new ArrayList<>();
    CountDownTimer QuestTimer;
    private boolean isPaused = false;
    private boolean isFirstStart = true;
    private long timeRemaining = 0;
    long millisInFuture = 60000;
    long countDownInterval = 1000;
    //private MediaPlayer countdown_sound;
    String Answcall;
    int QuestAnswIter;
    //boolean sound_start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nemoy_vopros);

        TimeView = (TextView) findViewById(R.id.txtTimer);
        QuestAnswView = (TextView) findViewById(R.id.txtQuest_Answ);
        QuestAnswView.setMovementMethod(new ScrollingMovementMethod());
        Pause = (Button) findViewById(R.id.btnPausRestart);
        Pause.setEnabled(false);
        Answer = (Button) findViewById(R.id.btnAnsw);
        Answer.setEnabled(false);
        Infobutton = (ImageButton) findViewById(R.id.btnNemoy_rules);
        SettingsButton = (ImageButton) findViewById(R.id.btnNemoy_settings);
        //Infobutton_lefty = (ImageButton) findViewById(R.id.btnNemoy_rules2);
        //SettingsButton_lefty = (ImageButton) findViewById(R.id.btnNemoy_settings2);
        //countdown_sound = MediaPlayer.create(this, R.raw.countdown);
        button_rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_anime);
        button_rotation.setAnimationListener(this);

        try {
            Questions = redDataQ("Questions.txt");
            Answers = redDataA("Answers.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Override
    //protected void onUserLeaveHint() {
    //    super.onUserLeaveHint();
    //    countdown_sound.pause();
    //}

    //@Override
    //public void onBackPressed() {
    //    super.onBackPressed();
    //    countdown_sound.pause();
    //}

    @Override
    public void onResume(){
    //    if (sound_start == false) { }
    //    else  { countdown.seekTo(0); countdown.start();}
        //setLeftyMode();
        super.onResume();
    }

    public List<String> redDataQ(String fileNameQ) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Questions.txt");
        try {
            BufferedReader readerQ = new BufferedReader(new InputStreamReader(fis));
            String Quest = "";
            while ((Quest = readerQ.readLine()) != null) {
                Questions.add(Quest);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Questions;
    }

    public List<String> redDataA(String fileNameA) throws IOException {
        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = assManager.open("Answers.txt");
        try {
            BufferedReader readerA = new BufferedReader(new InputStreamReader(fis));
            String Answer = "";
            while ((Answer = readerA.readLine()) != null) {
                Answers.add(Answer);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Answers;
    }

    public void GenerateQuestCall(View view) throws IOException {
        isPaused = false;
        Pause.setEnabled(true);
        if(Questions.size() == 0){QuestAnswView.setText(" ");}
        else {
            Answer.setEnabled(true);
            if(isFirstStart == true) {
                isFirstStart = false;
                QuestAnswIter = (int) Math.floor(Math.random() * (Questions.size() - 0) + 0);
                String Questcall = Questions.get(QuestAnswIter);
                Answcall = Answers.get(QuestAnswIter);
                QuestAnswView.setText(Questcall);
                QuestTimer = new CountDownTimer(millisInFuture, countDownInterval) {

                    public void onTick(long millisUntilFinished) {
                        if(isPaused) {cancel();}
                        else {
                            TimeView.setText("" + millisUntilFinished / 1000);
                            timeRemaining = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        QuestAnswView.setText(Answcall);
                        //countdown_sound.pause();
                        //sound_start = false;
                        TimeView.setText("ВРЕМЯ");
                        Pause.setEnabled(false);
                    }
                }.start();
                //countdown_sound.start();
                //sound_start = true;
            }
            else {
                //countdown_sound.seekTo(0);
                QuestTimer.cancel();
                QuestAnswIter = (int) Math.floor(Math.random() * (Questions.size() - 0) + 0);
                String Questcall = Questions.get(QuestAnswIter);
                Answcall = Answers.get(QuestAnswIter);
                QuestAnswView.setText(Questcall);
                QuestTimer = new CountDownTimer(millisInFuture, countDownInterval) {

                    public void onTick(long millisUntilFinished) {
                        if(isPaused) {cancel();}
                        else {
                            TimeView.setText("" + millisUntilFinished / 1000);
                            timeRemaining = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        QuestAnswView.setText(Answcall);
                        //countdown_sound.pause();
                        //sound_start = false;
                        TimeView.setText("ВРЕМЯ");
                        Pause.setEnabled(false);
                    }
                }.start();
                //countdown_sound.start();
                //sound_start = true;
            }
        }
    }

    public void GenerateAnswCall(View view) throws IOException {
        QuestTimer.cancel();
        QuestTimer.onFinish();
    }

    public void PauseCall(View view) throws IOException {
        if (isPaused == false)
        {
            isPaused = true;
            //countdown_sound.pause();
        }
        else {
            isPaused = false;
            //countdown_sound.seekTo(0);
            QuestTimer = new CountDownTimer(timeRemaining, countDownInterval) {

                public void onTick(long millisUntilFinished) {
                    if(isPaused) {cancel();}
                    else {
                        TimeView.setText("" + millisUntilFinished / 1000);
                        timeRemaining = millisUntilFinished;
                    }
                }

                public void onFinish() {
                    QuestAnswView.setText(Answcall);
                    //countdown_sound.pause();
                    //sound_start = false;
                    TimeView.setText("ВРЕМЯ");
                    Pause.setEnabled(false);
                }
            }.start();
            //countdown_sound.start();
            //sound_start = true;
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

    public void NemoySettingsCall(View view) {
        SettingsButton.startAnimation(button_rotation);
    }

    public void RulesCall(View view){
        final String[] RulesArray = {"1.Выберите того, кто будет объяснять",
                "2.Выберите очередность","3.Получите вопрос",
                "4.Не используя слов, постарайтесь сделать так, чтобы отгадывающий назвал загаданное слово",
                "5.Постарайтесь уложиться в одну минуту(не обязательно)",
                "6.Не нажимайте сразу кнопку ответа(не обязательно)",
                "7.Веселой игры","Узнайте, кто самый умный мим на вечеринке?)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(NemoyVoprosActivity.this);
        builder.setTitle("Немой вопрос")
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