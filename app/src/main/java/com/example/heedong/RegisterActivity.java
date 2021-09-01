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

        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_passwordcheck = findViewById(R.id.register_passwordcheck);

        Button register_register = findViewById(R.id.register_register);

        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!register_email.getText().toString().equals("") && !register_password.getText().toString().equals("")) {


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


                                            HashMap<Object, String> hashMap = new HashMap<>();

                                            hashMap.put("uid", uid);
                                            hashMap.put("email", email);


                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference reference = database.getReference("Users");
                                            reference.child(uid).setValue(hashMap);


                                            Snackbar.make(register_layout, "회원가입에 성공했습니다.", Snackbar.LENGTH_SHORT).show();

                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Snackbar.make(register_layout, "회원가입 생성 오류입니다.", Snackbar.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                    } else {
                        Snackbar.make(register_layout, "비밀번호가 틀렸습니다.", Snackbar.LENGTH_SHORT).show();

                    }

                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Snackbar.make(register_layout, "아이디와 비밀번호를 입력하세요", Snackbar.LENGTH_SHORT).show();
                }
            }
        });



    }


}