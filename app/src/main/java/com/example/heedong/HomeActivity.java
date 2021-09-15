package com.example.heedong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class HomeActivity extends AppCompatActivity {


    ImageButton home_bottom_likes, home_bottom_chat, home_bottom_home, home_bottom_search, home_bottom_profile;
    Button home_camera;

    private long backBtnTime = 0;

    public void onStart() {
        super.onStart();
//Fragment lifecycle 메서드 구현시, 항상 상위 클래스를 호출해야 한다.

     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        LikesFragment likesFragment = new LikesFragment();
        transaction.replace(R.id.frame, likesFragment);
        transaction.commit();



        home_bottom_likes =findViewById(R.id.home_bottom_likes);
        home_bottom_chat =findViewById(R.id.home_bottom_chat);
        home_bottom_home =findViewById(R.id.home_bottom_home);
        home_bottom_search =findViewById(R.id.home_bottom_search);
        home_bottom_profile =findViewById(R.id.home_bottom_profile);



        home_bottom_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                LikesFragment likesFragment = new LikesFragment();
                transaction.replace(R.id.frame, likesFragment);
                transaction.commit();
            }
        });

        home_bottom_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ChatFragment chatFragment = new ChatFragment();
                transaction.replace(R.id.frame, chatFragment);
                transaction.commit();
            }
        });

        home_bottom_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.frame, homeFragment);
                transaction.commit();
            }
        });

        home_bottom_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                transaction.replace(R.id.frame, searchFragment);
                transaction.commit();
            }
        });

        home_bottom_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.frame, profileFragment);
                transaction.commit();
            }
        });

        home_camera = findViewById(R.id.home_camera);

        home_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });





    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gaptime = curTime - backBtnTime;

        if(0 <= gaptime && 2000 >= gaptime){
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }
}