package com.example.openfashion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class details_page extends AppCompatActivity {

    EditText firstnameedittext,lastnameedittext,addressedittext,dobedittext,genderedittext,phoneedittext;
    FirebaseAuth mAuth;
    DatabaseReference profileref;
    String uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        firstnameedittext=(EditText) findViewById(R.id.firstnameedittext);
        lastnameedittext=(EditText) findViewById(R.id.lastnameedittext);
        addressedittext=(EditText) findViewById(R.id.addressedittext);
        dobedittext=(EditText) findViewById(R.id.dobedittext);
        genderedittext=(EditText) findViewById(R.id.genderedittext);
        phoneedittext=(EditText) findViewById(R.id.phoneedittext);

        mAuth=FirebaseAuth.getInstance();
        uid= mAuth.getCurrentUser().getUid();
        profileref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firsrnamedata,lastnamedata,addressdata,phonedata;

                firsrnamedata=snapshot.child("FirstName").getValue().toString();
                lastnamedata=snapshot.child("FirstName").getValue().toString();
                addressdata=snapshot.child("FirstName").getValue().toString();
                phonedata=snapshot.child("FirstName").getValue().toString();

                firstnameedittext.setText(firsrnamedata);
                lastnameedittext.setText(lastnamedata);
                addressedittext.setText(addressdata);
                phoneedittext.setText(phonedata);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Intent intent = getIntent();

        String Firstname = intent.getStringExtra("Firstname");
        String Lastname = intent.getStringExtra("Lastname");
        String Address = intent.getStringExtra("Address");
        String Dob = intent.getStringExtra("Dob");
        String Gender = intent.getStringExtra("Gender");
        String number = intent.getStringExtra("number");


        firstnameedittext.setText(Firstname);
        lastnameedittext.setText(Lastname);
        addressedittext.setText(Address);
        dobedittext.setText(Dob);
        genderedittext.setText(Gender);
        phoneedittext.setText(number);


    }
}