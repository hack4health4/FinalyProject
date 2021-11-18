package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText mEmail, mPass1;
    private Button mBtnLogin;
    private ProgressDialog progressDialog;
    private CheckBox mShow;
    String emailPaterns = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView mSignUp = (TextView) findViewById(R.id.btn_login2);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        mBtnLogin = (Button) findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);

        mShow = (CheckBox) findViewById(R.id.show_pass);
        mEmail = (EditText) findViewById(R.id.email);
        mPass1 = (EditText) findViewById(R.id.pass1);
        //hide password
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mShow.isChecked()) mPass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else mPass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAuth();
            }
        });

        // go to the Register activity by pressing "sign up"
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NurseN.class);
                startActivity(intent);
            }
        });
    }
    public void sendUserToNextActivity(){
        Intent intent1 = new Intent(Login.this, PatientActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
    }
    private void performAuth(){
        String email = mEmail.getText().toString();
        String p1 = mPass1.getText().toString();

        if (!email.matches(emailPaterns))
            mEmail.setError("Enter correct Email");
        else if (p1.isEmpty() || p1.length() < 6)
            mPass1.setError("Enter proper password");
        else {
            progressDialog.setMessage("Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, p1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}