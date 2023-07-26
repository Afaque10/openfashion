package com.example.openfashion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class otp_verification_page extends AppCompatActivity {

    EditText phonenumberedittext,otpone,otptwo,otpthree,otpfour,otpfive,otpsix;
    Button sendotp,verifotp;
    ProgressBar prgsendotp,prgs








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_page);

        phonenumberedittext = (EditText) findViewById(R.id.phonenumberedittext);

        otpone = (EditText) findViewById(R.id.otpdigit1);
        otptwo = (EditText) findViewById(R.id.otpdigit2);
        otpthree = (EditText) findViewById(R.id.otpdigit3);
        otpfour = (EditText) findViewById(R.id.otpdigit4);
        otpfive = (EditText) findViewById(R.id.otpdigit5);
        otpsix = (EditText) findViewById(R.id.otpdigit6);

    }
}