package com.example.trainingfavemobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.activities.HomeActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    EditText et_email, et_password;
    Button btn_login;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        progressBar = view.findViewById(R.id.progressBar_login);

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        et_email = view.findViewById(R.id.et_emailLogin);
        et_password = view.findViewById(R.id.et_passwordLogin);
        btn_login = view.findViewById(R.id.btn_login);

        et_email.setText(sharedPreferences.getString("email",""));
        et_password.setText(sharedPreferences.getString("password",""));
        editor.putString("email","");
        editor.apply();
        editor.putString("password", "");
        editor.apply();

        btn_login.setOnClickListener(view1 -> {
            progressBar.setVisibility(View.VISIBLE);

            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            progressBar.setVisibility(View.GONE);

            if (TextUtils.isEmpty(email)){
                Toast.makeText(getContext(), "Insert username!", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(password)){
                Toast.makeText(getContext(), "Insert password!", Toast.LENGTH_LONG).show();
            }
            else{
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(!task.isSuccessful()){
                        Toast.makeText(getContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

            }
        });
        return view;
    }
}