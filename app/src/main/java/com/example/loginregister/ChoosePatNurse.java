/*

package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChoosePatNurse extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pat_nurse);
        private ImageView mImgPat;
        private ImageView mImgNurse;
        mImgPat = (ImageView) findViewById(R.id.img_pat);
        mImgNurse = (ImageView) findViewById(R.id.img_nurse);

        mImgPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePatNurse.this, PatientActivity.class);
                startActivity(intent);
            }
        });

        mImgNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChoosePatNurse.this, NurseActivity.class);
                startActivity(intent1);
            }
        });



    }
}*/