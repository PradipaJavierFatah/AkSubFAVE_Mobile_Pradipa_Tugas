package com.example.trainingfavemobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.fragments.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    TextView tv_fullName;
    Button btn_showdb, btn_logout;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
    };
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference userRef;
    String full_name;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://fave-mobile-final-project-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mAuth.addAuthStateListener(authStateListener);
        firebaseUser = mAuth.getCurrentUser();
        userRef = firebaseDatabase.getReference().child("users").child(firebaseUser.getUid());
        tv_fullName = findViewById(R.id.tv_fullName);
        btn_showdb = findViewById(R.id.btn_showDatabase);
        btn_logout = findViewById(R.id.btn_logout);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                full_name = snapshot.child("full_name").getValue().toString();
                tv_fullName.setText(full_name);

                gender = snapshot.child("gender").getValue().toString();
                if(gender.equals("male")){
                    tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_face_24,0,0,0);
                }
                else if(gender.equals("female")){
                    tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_face_3_24,0,0,0);
                }
                else{
                    tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_android_24,0,0,0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_showdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }
}