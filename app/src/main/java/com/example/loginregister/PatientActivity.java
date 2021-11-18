package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ImageView img = (ImageView) findViewById(R.id.imageD2);
        TextView nom = (TextView) findViewById(R.id.nameD);
        TextView special = (TextView) findViewById(R.id.Specialite);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, NurseActivity.class);
                startActivity(intent);
            }
        });

        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, NurseActivity.class);
                startActivity(intent);
            }
        });

        nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, NurseActivity.class);
                startActivity(intent);
            }
        });

    }
}