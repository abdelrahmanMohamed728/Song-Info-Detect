package com.example.abdo.songinfodetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    Button btn1;
    AudioManager a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        btn1 = findViewById(R.id.btn1);

    }
     BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            String cmd = intent.getStringExtra("command");
            String artist = intent.getStringExtra("artist");
            String album = intent.getStringExtra("album");
            String track = intent.getStringExtra("track");
            txt2.setText("Artist : "+artist);
            txt3.setText("Album : "+album);
            txt4.setText("Track : "+track);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
         a = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a.isMusicActive()) {
                    txt1.setText("Running");
                    IntentFilter iF = new IntentFilter();
                    iF.addAction("com.android.music.metachanged");
                    iF.addAction("com.android.music.playstatechanged");
                    iF.addAction("com.android.music.playbackcomplete");
                    iF.addAction("com.android.music.queuechanged");
                    registerReceiver(mReceiver, iF);

                }
                else
                {
                    txt1.setText("Not Running");
                    txt2.setText(" ");
                    txt3.setText(" ");
                    txt4.setText(" ");
                }
            }
        });


    }

}
