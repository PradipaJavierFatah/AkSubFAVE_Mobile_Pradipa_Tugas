package com.example.trainingfavemobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.trainingfavemobile.fragments.LoginFragment;
import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.fragments.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FirebaseApp.initializeApp(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.login:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_signin, new LoginFragment()).commit();
                        return true;
                    case R.id.register:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_signin, new RegisterFragment()).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.login);
    }
}