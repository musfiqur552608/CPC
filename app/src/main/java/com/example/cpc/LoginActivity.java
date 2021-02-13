package com.example.cpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Animation animation_fadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utils.blackIconStatusBar(LoginActivity.this,R.color.light_Background);

        layout = findViewById(R.id.layout_min);

        animation_fadein = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.VISIBLE);
                layout.setAnimation(animation_fadein);
            }
        },1000);

    }
}