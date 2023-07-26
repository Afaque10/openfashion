package com.example.openfashion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    final Handler handler = new Handler(Looper.getMainLooper());//handler object creation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent a = new Intent(getApplicationContext(), otp_verification_page.class);//intent object creation
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//registering flags
                    startActivity(a);//starting the intent
                    finish();
                }
        }, 1500);
    }
}