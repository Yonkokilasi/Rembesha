package com.example.bubbles.rembesha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeginingActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.emailLogin) Button mEmail;
    @Bind(R.id.googleLogin) Button mGoogle;
    @Bind(R.id.signupLogin) Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begining);
        ButterKnife.bind(this);
        mEmail.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
        mGoogle.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == mEmail) {
            Intent intent = new Intent(BeginingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(BeginingActivity.this, "I love Email", Toast.LENGTH_SHORT).show();
        }
        if (v == mSignUp) {
            Intent intent = new Intent(BeginingActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(BeginingActivity.this, "I see you're new here", Toast.LENGTH_SHORT).show();
        }
        if (v == mGoogle) {
            Toast.makeText(BeginingActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        }

    }


}


