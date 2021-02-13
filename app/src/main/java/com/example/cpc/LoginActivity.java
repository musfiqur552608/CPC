package com.example.cpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import soup.neumorphism.NeumorphButton;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Animation animation_fadein;
    private TextView register;

    private EditText email, password;
    private NeumorphButton singIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Utils.blackIconStatusBar(LoginActivity.this,R.color.light_Background);

        layout = findViewById(R.id.layout_min);
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        singIn = findViewById(R.id.signinBtn);

        animation_fadein = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.VISIBLE);
                layout.setAnimation(animation_fadein);
            }
        },1000);

        register = findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });
    }

    private void AllowUserToLogin() {
        String useremail = email.getText().toString();
        String userpass = password.getText().toString();

        if(TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "Please enter your email...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userpass)){
            Toast.makeText(this, "Please enter your password...", Toast.LENGTH_SHORT).show();
        }

        else{
            mAuth.signInWithEmailAndPassword(useremail, userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Logged in successful...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}