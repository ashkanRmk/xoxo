package com.example.smn.xoxo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Fingerprint extends AppCompatActivity {


    TextView txt_HoldFInger, txt_countdownTime, txt_scanning;
    Button btn_fingerprint;
    long then = 0;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fingerprint);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        txt_countdownTime = (TextView) findViewById(R.id.Text_countdown);
        txt_HoldFInger = (TextView) findViewById(R.id.text_HoldFinger);
        txt_scanning = (TextView) findViewById(R.id.Text_scan);
        btn_fingerprint = (Button) findViewById(R.id.Button_fingerprint);
        vibe = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/W_kamran Bold.ttf");

        txt_countdownTime.setTypeface(font);
        txt_scanning.setTypeface(font);
        txt_HoldFInger.setTypeface(font);


        final CountDownTimer countdown = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txt_countdownTime.setText(Long.toString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                txt_countdownTime.setText("0");
                long milliseconds = 50;
                vibe.vibrate(milliseconds);


                final ProgressDialog dialog = new ProgressDialog(Fingerprint.this);
                dialog.setTitle("لطفا صبر کنید");
                dialog.setMessage("در حال محاسبه...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.show();

                long delayInMillis = 5000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        startActivity(new Intent(Fingerprint.this, Result.class));
                        finish();

                    }
                }, delayInMillis);

            }
        };


        final Animation center_to_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.center_to_left);
        final Animation right_to_center = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_center);


        btn_fingerprint.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = (Long) System.currentTimeMillis();
                    txt_scanning.startAnimation(right_to_center);
                    txt_scanning.setVisibility(View.VISIBLE);
                    countdown.start();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countdown.cancel();
                    txt_scanning.startAnimation(center_to_left);
                    txt_scanning.setVisibility(View.INVISIBLE);
                    if (((Long) System.currentTimeMillis() - then) < 5000) {
                        Toast.makeText(Fingerprint.this, "Click More!", Toast.LENGTH_SHORT).show();
                        txt_countdownTime.setText("");
                        long milliseconds = 60;
                        vibe.vibrate(milliseconds);
                    }
                }
                return true;


            }
        });


    }

}