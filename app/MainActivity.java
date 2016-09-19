package com.example.smn.xoxo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String regexString = "[0-9]!@#$%^&*_-+=/|}]:;.><,`";
    RadioButton check_u_male, check_u_female, check_prtnr_male, check_prtnr_female;
    TextView txt_u, txt_prtnr;
    EditText edit_ur_name, edit_prtnr_name, edit_u_age, edit_prtnr_age;
    Button btn_next;
    String prtnr_a, prtnr_n, u_a, u_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        check_prtnr_female = (RadioButton) findViewById(R.id.Check_prtnr_fmle);
        check_prtnr_male = (RadioButton) findViewById(R.id.Check_prtnr_mle);
        check_u_female = (RadioButton) findViewById(R.id.Check_u_fmle);
        check_u_male = (RadioButton) findViewById(R.id.Check_u_mle);
        edit_prtnr_name = (EditText) findViewById(R.id.Edit_prtnr_name);
        edit_ur_name = (EditText) findViewById(R.id.Edit_ur_name);
        edit_prtnr_age = (EditText) findViewById(R.id.Edit_prtnr_age);
        edit_u_age = (EditText) findViewById(R.id.Edit_u_age);
        btn_next = (Button) findViewById(R.id.Button_next);
        txt_u = (TextView) findViewById(R.id.text_u);
        txt_prtnr = (TextView) findViewById(R.id.text_prtnr);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/W_kamran Bold.ttf");

        txt_prtnr.setTypeface(font);
        txt_u.setTypeface(font);
        btn_next.setTypeface(font);
        edit_prtnr_age.setTypeface(font);
        edit_u_age.setTypeface(font);
        edit_prtnr_name.setTypeface(font);
        edit_ur_name.setTypeface(font);
        check_prtnr_female.setTypeface(font);
        check_prtnr_male.setTypeface(font);
        check_u_female.setTypeface(font);
        check_u_male.setTypeface(font);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                prtnr_a = edit_prtnr_age.getText().toString();
                prtnr_n = edit_prtnr_name.getText().toString();
                u_a = edit_u_age.getText().toString();
                u_n = edit_ur_name.getText().toString();


                if (TextUtils.isEmpty(u_n)) {
                    edit_ur_name.setError("اسمتو وارد کن!");
                }

                if (TextUtils.isEmpty(u_a)) {
                    edit_u_age.setError("سنت رو وارد کن!");

                }

                if (TextUtils.isEmpty(prtnr_n)) {
                    edit_prtnr_name.setError("اسمش رو وارد کن!");
                }

                if (TextUtils.isEmpty(prtnr_a)) {
                    edit_prtnr_age.setError("سنش رو وارد کن!");
                }

                startActivity(new Intent(MainActivity.this, Fingerprint.class));
                finish();


                SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                preferences.edit().putString("key", prtnr_n);


//                if (TextUtils.isEmpty(u_n) != true && TextUtils.isEmpty(u_a) != true && TextUtils.isEmpty(prtnr_n) != true && TextUtils.isEmpty(prtnr_a) != true) {
//
////                    SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
////                    preferences.edit().putString("key", prtnr_n);
//                    startActivity(new Intent(MainActivity.this, Fingerprint.class));
//                    finish();
//                }
            }
        });

    }
}
