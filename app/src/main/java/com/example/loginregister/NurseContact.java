package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class NurseContact extends AppCompatActivity {

    private ImageView mPhone;
    private ImageView mMessage;
    private ImageView mEmail;
    //from DB
    private String email = "abilak4k@gmail.com";
    private String phone = "+21651981216";
    private String subject = "abilak";
    private final String text = "jofezj ozejfo jezojfo zejfj";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        mPhone = (ImageView) findViewById(R.id.img_phone);
        mEmail = (ImageView) findViewById(R.id.img_email);
        mMessage = (ImageView) findViewById(R.id.img_msg);

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NurseContact.this, Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED)
                    call();
                else
                    ActivityCompat.requestPermissions(NurseContact.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NurseContact.this, Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED)
                    sendMessage();
                else
                    ActivityCompat.requestPermissions(NurseContact.this, new String[]{Manifest.permission.SEND_SMS}, 100);
            }
        });
    }



//    public void writeToDataBase() {
//    }
//
//    Nurse nurse = new Nurse(email, subject, phone);
//    db.getReference("nurse").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(nurse).addOnCompleteListener(new OnCompleteListener<Void>() {
//        @Override
//        public void onComplete (@NonNull Task < Void > task) {
//            if (task.isSuccessful()) {
//                Toast.makeText(NurseActivity.this, "Nurse has been registered successfully", Toast.LENGTH_LONG).show();
//            }
//        }
//    });




    private void call(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+ phone));
        startActivity(callIntent);
    }
    private void sendEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

    }
    private void sendMessage(){

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, text, null, null);

//        // getting intent and pendingIntent instance
//        Intent intent = new Intent(getApplicationContext(),NurseActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(getApplication(),0,intent,0);
//
//        //
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phone, null, text, pi, null);
    }


}