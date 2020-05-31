package com.example.tamagotchi_star;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private int countTicks;
    private ProgressBar lebensanzeige;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds= 60000;
    private boolean timerRunning;
    Charakter c1 = new Charakter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        countdownText = findViewById(R.id.countdownText);
        countdownButton = findViewById(R.id.countdownButton);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        lebensanzeige.setProgress(c1.getHp());


    }

    public void startStop(){
        if(timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds,1000
        ) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds= millisUntilFinished;
                countTicks+=1;
                if (countTicks>=60){
                    c1.wirdHungrig();
                    c1.wirdDreckig();
                    c1.wirdMüde();
                    c1.updateHp();
                    countTicks=0;
                    lebensanzeige.setProgress(c1.getHp());
                }
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countdownButton.setText("Pause");
        timerRunning=true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning= false;
        countdownButton.setText("Start");
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds/600000;
        int seconds = (int) timeLeftInMilliseconds%600000/1000;

        String timeLeftText;
        timeLeftText= "" + minutes;
        timeLeftText+= ":";
        if(seconds<10)timeLeftText+="0";
        timeLeftText+=seconds;
        countdownText.setText(timeLeftText);

    }

}
