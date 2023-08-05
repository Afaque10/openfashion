package com.example.openfashion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_verification_page extends AppCompatActivity {

    EditText phonenumberedittext, otpone, otptwo, otpthree, otpfour, otpfive, otpsix;
    Button sendotp, verifyfotp;
    ProgressBar prgsendotp, prgverifyotp;
    TextView phonenumbertextview, signbutton;
    FirebaseAuth mAuth;
    LinearLayout otpsendlayout, otpverifylayout;
    String OtpId, Phonenumber, finalotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_page);

        phonenumberedittext = (EditText) findViewById(R.id.phonenumberedittext);

        prgsendotp = (ProgressBar) findViewById(R.id.progressbarotpsend);
        prgverifyotp = (ProgressBar) findViewById(R.id.progressbarotpverify);
        signbutton = (TextView) findViewById(R.id.signinbutton);
        phonenumbertextview = (TextView) findViewById(R.id.phone_textview);
        mAuth = FirebaseAuth.getInstance();
        otpsendlayout = (LinearLayout) findViewById(R.id.sendotplayout);
        otpverifylayout = (LinearLayout) findViewById(R.id.verifyotplayout);


        otpone = (EditText) findViewById(R.id.otpdigit1);
        otptwo = (EditText) findViewById(R.id.otpdigit2);
        otpthree = (EditText) findViewById(R.id.otpdigit3);
        otpfour = (EditText) findViewById(R.id.otpdigit4);
        otpfive = (EditText) findViewById(R.id.otpdigit5);
        otpsix = (EditText) findViewById(R.id.otpdigit6);

        sendotp = (Button) findViewById(R.id.next_button);
        verifyfotp = (Button) findViewById(R.id.otpverify);

        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(otp_verification_page.this, details_page.class);
                startActivity(x);
            }
        });
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        verifyfotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyotp();


            }
        });

    }

    private void verifyotp() {
        String number1 = otpone.getText().toString().trim();
        String number2 = otptwo.getText().toString().trim();
        String number3 = otpthree.getText().toString().trim();
        String number4 = otpfour.getText().toString().trim();
        String number5 = otpfive.getText().toString().trim();
        String number6 = otpsix.getText().toString().trim();
        finalotp = number1 + number2 + number3 + number4 + number5 + number6;

        if (number1.isEmpty() || number2.isEmpty() || number3.isEmpty() || number4.isEmpty() || number5.isEmpty() || number6.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Otp Cannot Be Blank", Toast.LENGTH_LONG).show();
        } else {
            if (finalotp.length() != 6) {

                Toast.makeText(getApplicationContext(), "Otp Length Less Then Six", Toast.LENGTH_LONG).show();
            } else {

                prgverifyotp.setVisibility(View.VISIBLE);
                PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(OtpId, finalotp);
                signInWithPhoneAuthCredential(authCredential);

            }

        }


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential authCredential) {
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    prgverifyotp.setVisibility(View.GONE);
                    Intent x = new Intent(otp_verification_page.this, details_page.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(getApplicationContext(), "Otp Verified successfully", Toast.LENGTH_LONG).show();
                    startActivity(x);
                    finish();

                } else {
                    prgverifyotp.setVisibility(View.GONE);
                    String msg = task.getException().getMessage();
                    if (msg.equals("The sms code has expired. Please re-send the verification code to try again.")) {
                        Toast.makeText(getApplicationContext(), "Code Expired", Toast.LENGTH_LONG).show();
                    } else if (msg.equals("The sms verification code used to create the phone auth credential is invalid. Please resend the verification code sms and be sure use the verification code provided by the user.")) {
                        Toast.makeText(getApplicationContext(), "Invalid Code", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                    }


                }


            }
        });
    }

    private void sendOTP() {
        Phonenumber = phonenumberedittext.getText().toString().trim();
        if (Phonenumber.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Phone Number Should not be empty", Toast.LENGTH_LONG).show();

        } else if (Phonenumber.length() != 10) {
            Toast.makeText(getApplicationContext(), "Phone Number Should be 10 Digits", Toast.LENGTH_LONG).show();
        } else {
            prgsendotp.setVisibility(View.VISIBLE);
            PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth).
                    setPhoneNumber("+91" + Phonenumber).
                    setTimeout(60L, TimeUnit.SECONDS).
                    setActivity(this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            prgsendotp.setVisibility(View.GONE);
                            otpsendlayout.setVisibility(View.GONE);
                            phonenumbertextview.setText("+91 " + Phonenumber);
                            otpverifylayout.setVisibility(View.VISIBLE);
                            OtpId = verificationId;
                            Toast.makeText(getApplicationContext(), "OTP sent successfully", Toast.LENGTH_LONG).show();
                            //mResendToken = token;
                        }
                    }).build();
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);


        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent mainIntent = new Intent(otp_verification_page.this, home_page.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            finish();
        }

    }
}


























    }
