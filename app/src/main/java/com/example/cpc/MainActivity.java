package com.example.cpc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView cpc_logo, img_bottom;
    TextView cpc_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.blackIconStatusBar(MainActivity.this,R.color.light_Background);

        cpc_text = findViewById(R.id.cpc_text);
        cpc_logo = findViewById(R.id.cpc_logo);
        img_bottom = findViewById(R.id.img_bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, Pair.create(cpc_logo,"logo"),
                        Pair.create(img_bottom, "img_tree"),
                        Pair.create(cpc_text, "logo_text"));

                startActivity(intent,options.toBundle());
            }
        },3000);
    }
}