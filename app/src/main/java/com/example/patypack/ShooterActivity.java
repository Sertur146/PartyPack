package com.example.patypack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class ShooterActivity extends AppCompatActivity implements Animation.AnimationListener {

    private TextView textView;
    private EditText editText;
    private ListView ListOfPlayers, ListOfPoints;
    public ArrayAdapter<String> spnames_adapter, sppoints_adapter;
    ArrayList<String> shot_players = new ArrayList<>();
    ArrayList<String> shot_points = new ArrayList<>();
    private ImageButton Infobutton, SettingsButton, ResetClipButton,Infobutton_lefty,
            SettingsButton_lefty;
    private Button btnShot;
    private ImageView image;
    private Animation animation, button_rotation;
    private AnimationDrawable animationlist;
    private MediaPlayer shot, reload_pat, clip_snapped;
    int numberofbullet = 0;
    boolean btnShotClickCheck, btnSettingsClickCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooter);

        textView = (TextView) findViewById(R.id.nameBUH);
        editText = (EditText) findViewById(R.id.editBUHgamer);
        Infobutton = (ImageButton) findViewById(R.id.btnShooter_rules);
        SettingsButton = (ImageButton) findViewById(R.id.btnShooter_settings);
        ResetClipButton = (ImageButton) findViewById(R.id.btnClip_reset);
        //Infobutton_lefty = (ImageButton) findViewById(R.id.btnShooter_rules2);
        //SettingsButton_lefty = (ImageButton) findViewById(R.id.btnShooter_settings2);
        btnShot = (Button) findViewById(R.id.btnShot);
        ListOfPlayers = (ListView) findViewById(R.id.lstPlayers);
        ListOfPoints = (ListView) findViewById(R.id.lstPoints);
        spnames_adapter = new ArrayAdapter<>(this, R.layout.row, R.id.txtSPNames, shot_players);
        sppoints_adapter= new ArrayAdapter<>(this, R.layout.row, R.id.txtSPNames, shot_points);
        ListOfPlayers.setAdapter(spnames_adapter);
        ListOfPoints.setAdapter(sppoints_adapter);

        image = findViewById(R.id.clip);
       //animationlist = (AnimationDrawable) image.getBackground();
        animation = AnimationUtils.loadAnimation(
                this, R.anim.clip_anime);
        animation.setAnimationListener(this);
        button_rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_anime);
        button_rotation.setAnimationListener(this);
        shot = MediaPlayer.create(this, R.raw.shot);
        reload_pat = MediaPlayer.create(this, R.raw.reload_pat);
        clip_snapped = MediaPlayer.create(this, R.raw.clip_snapped);

    }

    @Override
    protected void onResume() {
        //setLeftyMode();
        super.onResume();
    }

    public void AddBUHCall(View view) {
        String strBUHgamer = editText.getText().toString();
        shot_players.add(strBUHgamer);
        shot_points.add("0");
        spnames_adapter.notifyDataSetChanged();
        sppoints_adapter.notifyDataSetChanged();
        editText.setText("");
    }

    public void BUHCall(View view) {
        if(shot_players.size() == 0){textView.setText("Имя Игрока");}
        else {
            int i = (int) Math.floor(Math.random() * (shot_players.size() - 0) + 0);
            String nameofBUH = shot_players.get(i);
            int pointplus = Integer.parseInt(shot_points.get(i).trim()) + 1;
            if(numberofbullet<6)
            {
                shot_points.set(i, Integer.toString(pointplus));
                sppoints_adapter.notifyDataSetChanged();
            }
            textView.setText(nameofBUH);
            numberofbullet += 1;
            image.startAnimation(animation);
            if (numberofbullet > 6) numberofbullet = 0;
        }
    }

    public void DeleteBUHCall(View view) {
        String strBUHloser = textView.getText().toString();
        Iterator<String> arrayIterator1 = shot_players.iterator();
        int di = 0;
        while (arrayIterator1.hasNext())
        {
            String nexti =arrayIterator1.next();
            if (nexti.equals(strBUHloser))
            {
                arrayIterator1.remove();
                shot_points.remove(di);
            }
            di += 1;
        }
        spnames_adapter.notifyDataSetChanged();
        sppoints_adapter.notifyDataSetChanged();
        textView.setText("Имя Игрока");
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

    public void ShooterSettingsCall(View view) {
        SettingsButton.startAnimation(button_rotation);
        btnSettingsClickCheck = true;
    }

    public void ClipResetCall(View view){
        numberofbullet = 0;
        ResetClipButton.startAnimation(button_rotation);
        image.startAnimation(animation);
    }

    public void RulesCall(View view){
        final String[] RulesArray = {"1.Добавьте игроков","2.Сделайте выстрел","3.Постарайтесь не быть упитым)",
                "4.Веселой игры","Королевская битва начинается!"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ShooterActivity.this);
        builder.setTitle("Shot Shooter")
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
        if (numberofbullet == 1)
        {
            //clip_snapped.pause();
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_1);
            shot.start();
        }
        if (numberofbullet == 2)
        {
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_2);
            shot.start();
        }
        if (numberofbullet == 3)
        {
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_3);
            shot.start();
        }
        if (numberofbullet == 4)
        {
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_4);
            shot.start();
        }
        if (numberofbullet == 5)
        {
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_5);
            shot.start();
        }
        if (numberofbullet == 6)
        {
            shot.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_empty);
            shot.start();
            textView.setText("Empty");
            btnShot.setText("Перезарядить");
        }
        if (numberofbullet == 0 && btnSettingsClickCheck == false)
        {
            shot.pause();
            clip_snapped.seekTo(0);
            image.setImageResource(R.drawable.ic_clip_full);
            clip_snapped.start();
            textView.setText("Reload");
            btnShot.setText("Сделать выстрел");
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (btnSettingsClickCheck == true){
            Intent intent = new Intent(getApplicationContext(), com.example.patypack.SettingsActivity.class);
            startActivity(intent);
            btnSettingsClickCheck = false;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}