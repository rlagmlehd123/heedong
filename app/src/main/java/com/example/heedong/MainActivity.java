package com.example.heedong;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button main_login, main_register;
    private ImageView main_loading;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        main_login = findViewById(R.id.main_login);

        main_login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        main_register = findViewById(R.id.main_register);
        main_register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });


    }

    private void initView(){
        main_loading = (ImageView) findViewById(R.id.main_loading);
        animation = AnimationUtils.loadAnimation(this, R.anim.loading);
        main_loading.setAnimation(animation);
    }



}