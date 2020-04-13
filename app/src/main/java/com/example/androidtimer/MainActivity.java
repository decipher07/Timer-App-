package com.example.androidtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView ;
    SeekBar timerSeekBar ;
    Boolean counterIsActive = false ;
    Button goButton ;
    CountDownTimer countDownTimer;

    public void resetTimer (){
        counterIsActive = false ;
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
    }

    public void buttonClicked (View view) {

        if (!counterIsActive){

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    Log.i("Finished", "Timer All Done");
                    resetTimer();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    public void updateTimer(int secondsLeft){
        int mins = secondsLeft/60 ;
        int seconds = secondsLeft - (60*mins);
        String secondString ;
        secondString = Integer.toString(seconds);
        if (seconds <= 9)
            secondString = "0" + secondString;
        timerTextView.setText(Integer.toString(mins) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.countDownTextView);
        goButton = (Button) findViewById(R.id.goButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
