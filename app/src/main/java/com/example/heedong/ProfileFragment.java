package com.example.heedong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        Button fragment_logout = view.findViewById(R.id.fragment_logout);
        fragment_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);


                        Toast.makeText(getActivity(),"로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.create().show();


            }
        });
        Button fragment_delete = view.findViewById(R.id.fragment_delete);
        fragment_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("회원 탈퇴를 하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.getCurrentUser().delete();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(getActivity(),"탈퇴했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.create().show();


            }
        });

        return view;
    }
}