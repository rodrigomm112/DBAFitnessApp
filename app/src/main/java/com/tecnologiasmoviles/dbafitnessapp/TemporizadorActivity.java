package com.tecnologiasmoviles.dbafitnessapp;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TemporizadorActivity extends Activity {

    private static final long ComenzarMilisecs = 60000;

    private TextView mTextViewCountDown; //LA CUENTA REGRESIVA
    private Button mButtonStartPause; //OBEJTO DEL BUTTON
    private Button mButtonReset; ////BOTON DEL RESET PARA REINICIAR EL TIEMPO

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunnig;

    private long mTImeLeftInMillis = ComenzarMilisecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);
        mTextViewCountDown = findViewById(R.id.textcontador);

        mButtonStartPause = findViewById(R.id.btnstartpause);
        mButtonReset = findViewById(R.id.btnreset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(mTimerRunnig) {
                    pauseTimer();
                }else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();


    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTImeLeftInMillis, 1000) {//EL TIMER SE INICIA
            @Override
            public void onTick(long millisUntilFinished) {
                mTImeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunnig = false;
                mButtonStartPause.setText("start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunnig = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunnig = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        mTImeLeftInMillis = ComenzarMilisecs;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText(){
        int minutes = (int) (mTImeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTImeLeftInMillis / 1000) % 60;

        String timeLeftFromatted = String.format(Locale.getDefault(),"%02d:%02d" , minutes, seconds);
        mTextViewCountDown.setText(timeLeftFromatted);

    }

}