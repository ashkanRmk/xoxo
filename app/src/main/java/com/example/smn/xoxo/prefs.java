package com.example.smn.xoxo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;



public class prefs extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

            String myString = prefs.getString("myStringName", "string");
        }
    }