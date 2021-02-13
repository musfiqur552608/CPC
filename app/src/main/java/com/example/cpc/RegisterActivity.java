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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import soup.neumorphism.NeumorphButton;

public class RegisterActivity extends AppCompatActivity {
    private LinearLayout layout;
    private Animation animation_fadein;
    private TextView register;
    private EditText email, pass, conpass;
    private NeumorphButton registerBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Utils.blackIconStatusBar(RegisterActivity.this,R.color.light_Background);

        mAuth = FirebaseAuth.getInstance();

        layout = findViewById(R.id.layout_min);

        animation_fadein = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.fade_in);

        email = findViewById(R.id.email_et1);
        pass = findViewById(R.id.password_et1);
        conpass = findViewById(R.id.conpassword_et);
        registerBtn = findViewById(R.id.signiupBtn);
        register = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.VISIBLE);
                layout.setAnimation(animation_fadein);
            }
        },1000);


    }

    private void CreateNewAccount() {
        String useremail = email.getText().toString();
        String userpass = pass.getText().toString();
        String conuserpass = conpass.getText().toString();

        if(TextUtils.isEmpty(useremail))
        {
            Toast.makeText(this, "Please enter your email...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userpass))
        {
            Toast.makeText(this, "Please enter your password...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(conuserpass)){
            Toast.makeText(this, "Please confirm your password...", Toast.LENGTH_SHORT).show();
        }

        else if(!userpass.equals(conuserpass)){
            Toast.makeText(this, "Your password don't match, please write your correct password...", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(useremail, userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(RegisterActivity.this, "Account Created Successfully...", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error Occured: "+message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}