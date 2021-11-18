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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText mEmail, mUsername, mPhone, mPass1, mPass2;
    private Button mBtnRegister;
    private ProgressDialog progressDialog;
    private CheckBox mNurse;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    String emailPaterns = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    String phonepattern = "^[+]*[0-9]*$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mBtnRegister = (Button) findViewById(R.id.btn_register);
        progressDialog = new ProgressDialog(this);
        mNurse = (CheckBox) findViewById(R.id.nurse_box);

        mEmail = (EditText) findViewById(R.id.email);
        mUsername= (EditText) findViewById(R.id.username);
        mPhone = (EditText) findViewById(R.id.phone);
        mPass1 = (EditText) findViewById(R.id.pass1);
        mPass2 = (EditText) findViewById(R.id.pass2);

        //hide password
        mNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNurse.isChecked()){
                    mPass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    mPass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performeAuth();
            }
        });


        // go to the Login activity by pressing "sign in"
        TextView mSignIn = (TextView) findViewById(R.id.sign_in);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Register.this, Login.class);
                startActivity(intent1);
            }
        });
    }


    private void performeAuth(){
        String userName = mUsername.getText().toString();
        String email = mEmail.getText().toString();
        String p1 = mPass1.getText().toString();
        String p2 = mPass2.getText().toString();
        String ph = mPhone.getText().toString();
        if (userName.isEmpty() || userName.length()<4)
            mUsername.setError("Invalid username");
        else if(!email.matches(emailPaterns))
            mEmail.setError("Enter correct Email");
        else if(!ph.matches(phonepattern) || ph.length()!=12) mPhone.setError("Invalid phone number");
        else if(p1.isEmpty() || p1.length()<6)
            mPass1.setError("Enter proper password");
        else if(!p1.equals(p2))
            mPass2.setError("passwords don't match");
        else{
            progressDialog.setMessage("Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, p1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Register.this, "Registration successfully", Toast.LENGTH_SHORT).show();

//                        //use the patient class + database:
//                        Patient patient = new Patient(userName, email, ph);
//
//                        FirebaseDatabase.getInstance().getReference("Patient").child(FirebaseAuth.getInstance().
//                                getCurrentUser().getUid())
//                                .setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(Register.this,"Patient has been registered successfully",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });//***.child(user_id)
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, ""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        }

    private void sendUserToNextActivity(){
        mNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNurse.isChecked()) {
                    Intent intent = new Intent(Register.this, PatientActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent2 = new Intent(Register.this, NurseActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent2);
                }
            }
        });



    }
}