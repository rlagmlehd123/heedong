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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText register_nickname, register_email, register_password, register_passwordcheck;
    private View register_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        register_nickname = findViewById(R.id.register_nickname);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_passwordcheck = findViewById(R.id.register_passwordcheck);

        Button register_register = findViewById(R.id.register_register);

        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!register_email.getText().toString().equals("") && !register_password.getText().toString().equals("")) {

                    String nickname = register_nickname.getText().toString().trim();
                    String email = register_email.getText().toString().trim();
                    String password = register_password.getText().toString().trim();
                    String passwordcheck = register_passwordcheck.getText().toString().trim();

                    if (password.equals(passwordcheck)) {
                        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                                RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                            String email = user.getEmail();
                                            String uid = user.getUid();
                                            String name = register_nickname.getText().toString().trim();

                                            HashMap<Object, String> hashMap = new HashMap<>();

                                            hashMap.put("uid", uid);
                                            hashMap.put("email", email);
                                            hashMap.put("name", name);

                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference reference = database.getReference("Users");
                                            reference.child(uid).setValue(hashMap);

                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "회원가입 생성 오류입니다.", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(RegisterActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });






        register_layout = findViewById(R.id.register_layout);

        Button register_naver = findViewById(R.id.register_naver);

        register_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(register_layout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        Button register_kakao = findViewById(R.id.register_kakao);

        register_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(register_layout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        Button register_google = findViewById(R.id.register_google);

        register_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(register_layout, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

    }


}