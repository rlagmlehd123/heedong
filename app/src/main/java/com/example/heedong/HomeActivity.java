package com.example.heedong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    ImageButton home_bottom_likes, home_bottom_chat, home_bottom_home, home_bottom_search, home_bottom_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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



    }
}