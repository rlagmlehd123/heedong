package com.example.heedong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login_login, login_register, login_naver, login_kakao, login_google;
    EditText login_email, login_password;
    private FirebaseAuth firebaseAuth;
    private View loginlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        login_register = findViewById(R.id.login_register);

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_login = findViewById(R.id.login_login);

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){


                                 if(password.length() < 8){
                                     Toast.makeText(LoginActivity.this,"비밀번호는 8자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                                 } else {
                                    Toast.makeText(LoginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                            }

                        }

                        });
            }
        });

        loginlayout = findViewById(R.id.login_layout);

        login_naver = findViewById(R.id.login_naver);

        login_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(loginlayout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        login_kakao = findViewById(R.id.login_kakao);

        login_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(loginlayout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        login_google = findViewById(R.id.login_google);

        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(loginlayout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}