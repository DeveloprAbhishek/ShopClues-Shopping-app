package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvRegistrationArrowBack;
    private TextView mIvRegistration_Fb_logo;
    private CheckBox mCbRegisterWithPassword;
    private EditText mEtEnterYourEmailId_Registration, mEtMobileNumber, mEtPassword;
    private TextView mTvLoginNow;
    private Button mBtnRegister;
    private String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    private void initViews() {
        mIvRegistrationArrowBack = findViewById(R.id.ivRegistrationArrowBack);
        mIvRegistration_Fb_logo = findViewById(R.id.faceBook_option_registration);
        mCbRegisterWithPassword = findViewById(R.id.cbRegisterWithPassword);
        mEtEnterYourEmailId_Registration = findViewById(R.id.etEnterYourEmailId_Registration);
        mEtMobileNumber = findViewById(R.id.etMobileNumber);
        mEtPassword = findViewById(R.id.etPassword);
        mTvLoginNow = findViewById(R.id.tvLoginNow);
        mBtnRegister = findViewById(R.id.btnRegister);

        mIvRegistrationArrowBack.setOnClickListener(this);
        mEtEnterYourEmailId_Registration.setOnClickListener(this);
        mCbRegisterWithPassword.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mTvLoginNow.setOnClickListener(this);
        mIvRegistration_Fb_logo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.ivRegistrationArrowBack == v.getId()) {
            onBackPressed();
        } else if (R.id.etEnterYourEmailId_Registration == v.getId()) {
            mEtMobileNumber.setVisibility(View.VISIBLE);
            mCbRegisterWithPassword.setVisibility(View.VISIBLE);
        } else if (R.id.cbRegisterWithPassword == v.getId()) {
            if (mCbRegisterWithPassword.isChecked()) mEtPassword.setVisibility(View.VISIBLE);
            else mEtPassword.setVisibility(View.GONE);
        } else if (v.getId() == R.id.btnRegister) {
            boolean emailValid = isEmailValid();
            boolean mobileValid = isMobileNumberValid();
            boolean passwordValid = isPasswordValid();
            if (mCbRegisterWithPassword.isChecked() && emailValid && mobileValid && passwordValid) {
                RegisterNewUser();
            } else if (!mCbRegisterWithPassword.isChecked() && emailValid && mobileValid) {

            }
        } else if (v.getId() == R.id.tvLoginNow) {
            startActivity(new Intent(RegistrationActivity.this, LogInActivity.class));
        } else if (v.getId() == R.id.faceBook_option_registration) {

        }
    }

    private void RegisterNewUser() {
        String email = mEtEnterYourEmailId_Registration.getText().toString();
        String password = mEtPassword.getText().toString();
        String mobile = mEtMobileNumber.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(email, password, mobile);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, "You have successfully registered.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "Failed to registered! Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Failed to registered! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isEmailValid() {
        if (mEtEnterYourEmailId_Registration.getText().toString().matches(EMAIL_REGEX)) {
            return true;
        } else {
            mEtEnterYourEmailId_Registration.setError("Invalid email!");
            mEtEnterYourEmailId_Registration.requestFocus();
            return false;
        }
    }

    private boolean isMobileNumberValid() {
        if (mEtMobileNumber.getText().toString().length() == 10) {
            return true;
        } else {
            mEtMobileNumber.setError("Invalid Mobile Number!");
            mEtMobileNumber.requestFocus();
            return false;
        }
    }

    private boolean isPasswordValid() {
        if (mEtPassword.getText().toString().length() >= 6) {
            return true;
        } else {
            mEtPassword.setError("Please enter atleast 6 digit password;");
            mEtPassword.requestFocus();
            return false;
        }
    }
}