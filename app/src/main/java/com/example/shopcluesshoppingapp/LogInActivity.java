package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvRegisterNow;
    private Button mBtnLoginViaOTP, mBtnLoginViaPassword, mBtnLogin;
    private EditText mEtEnterEmail, mEtEnterPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        checkIfUserAuthenticated();
        initViews();
    }

    private void checkIfUserAuthenticated() {
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(this, "You are redirected to home page." + mAuth.getUid(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            Toast.makeText(this, "Please Login You are not authorized user!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void initViews() {
        mTvRegisterNow = findViewById(R.id.tvRegisterNow);
        mBtnLoginViaOTP = findViewById(R.id.btnLoginViaOTP);
        mBtnLoginViaPassword = findViewById(R.id.btnLoginViaPassword);
        mEtEnterEmail = findViewById(R.id.etEnterYourNumber);
        mBtnLogin = findViewById(R.id.btnLogin);
        mEtEnterPassword = findViewById(R.id.etEnterPassword);

        mTvRegisterNow.setOnClickListener(this);
        mBtnLoginViaOTP.setOnClickListener(this);
        mBtnLoginViaPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.tvRegisterNow == v.getId()) {
            startActivity(new Intent(LogInActivity.this, RegistrationActivity.class));
        } else if (R.id.btnLoginViaOTP == v.getId()) {

        } else if (R.id.btnLoginViaPassword == v.getId()) {
            mBtnLoginViaPassword.setVisibility(View.GONE);
            mBtnLoginViaOTP.setVisibility(View.GONE);
            mEtEnterPassword.setVisibility(View.VISIBLE);
            mBtnLogin.setVisibility(View.VISIBLE);

        } else if (R.id.btnLogin == v.getId()) {
            login();
        }
    }

    private void login() {
        String email = mEtEnterEmail.getText().toString();
        String password = mEtEnterPassword.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LogInActivity.this, HomeActivity.class));
                    Toast.makeText(LogInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void updateUI(FirebaseUser user) {

    }


}