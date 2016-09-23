package com.example.smn.xoxo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Result extends AppCompatActivity {


    String string_from_sp;
    Random rand = new Random();
    TextView txt_result, txt_prcntge;
    Button btn_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_result);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        txt_prcntge = (TextView) findViewById(R.id.Text_percentage);
        txt_result = (TextView) findViewById(R.id.Text_result);
        btn_again = (Button) findViewById(R.id.Button_again);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/DJB Cutouts-Hearts.ttf");

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/W_kamran Bold.ttf");

        txt_prcntge.setTypeface(font2);
        txt_result.setTypeface(font);
        btn_again.setTypeface(font);



        SharedPreferences preferences = getSharedPreferences("prefs",
                Context.MODE_PRIVATE);
        string_from_sp = preferences.getString("key", "");

        txt_result.setText("درصد عشق تو و " + string_from_sp);
        int n = rand.nextInt(100) + 1;
        if (n<50)
            n=n+50;

        txt_prcntge.setText("" + n + "%");


        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Result.this, MainActivity.class));
                finish();

            }
        });

    }
}