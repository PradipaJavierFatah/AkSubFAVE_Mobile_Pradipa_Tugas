package com.example.trainingfavemobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.activities.HomeActivity;
import com.example.trainingfavemobile.activities.SigninActivity;
import com.example.trainingfavemobile.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    EditText et_email, et_firstName, et_lastName, et_password, et_confPassword;
    Button btn_register;
    ProgressBar progressBar;
    RadioGroup rg_gender;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        progressBar = view.findViewById(R.id.progressBar_register);

        FirebaseApp.initializeApp(getActivity());
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://fave-mobile-final-project-default-rtdb.asia-southeast1.firebasedatabase.app/");

        et_email = view.findViewById(R.id.et_email);
        et_firstName = view.findViewById(R.id.et_firstname);
        et_lastName = view.findViewById(R.id.et_lastname);
        rg_gender = view.findViewById(R.id.rg_gender);
        et_password = view.findViewById(R.id.et_password);
        et_confPassword = view.findViewById(R.id.et_confPassword);
        btn_register = view.findViewById(R.id.btn_register);
        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        final String[] gender = new String[1];

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_male:
                        gender[0] = "male";
                        break;
                    case R.id.rb_female:
                        gender[0] = "female";
                        break;
                    case R.id.rb_none:
                        gender[0] = "none";
                        break;
                    default:
                        gender[0] = "";
                        break;
                }
            }
        });

        btn_register.setOnClickListener(view1 -> {
            progressBar.setVisibility(View.VISIBLE);

            String email = et_email.getText().toString();
            String firstname = et_firstName.getText().toString();
            String lastname = et_lastName.getText().toString();
            String fullname = firstname + " " + lastname;
            String password = et_password.getText().toString();
            String confPass = et_confPassword.getText().toString();

            progressBar.setVisibility(View.GONE);
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(gender[0]) || TextUtils.isEmpty(password) || TextUtils.isEmpty((confPass))) {
                Toast.makeText(getContext(), "Fill in all the required fields!", Toast.LENGTH_LONG).show();
            } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                Toast.makeText(getContext(), "Email must be valid", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 8) {
                Toast.makeText(getContext(), "Password must be longer than 8 characters", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confPass)) {
                Toast.makeText(getContext(), "Password must match Confirm Password", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder
                        .setTitle("Confirm Registration")
                        .setMessage("Submit?")
                        .setPositiveButton("Submit", (dialog,which) -> {
                            progressBar.setVisibility(View.VISIBLE);
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Register failed", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                userRef = firebaseDatabase.getReference("users").child(mAuth.getCurrentUser().getUid());
                                userRef.setValue(new User(email, fullname, gender[0]));
                                editor.putString("email",email);
                                editor.apply();
                                editor.putString("password",password);
                                editor.apply();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getContext(), SigninActivity.class));
                                getActivity().finish();
                            });
                        })
                        .setNegativeButton("Cancel",(dialog, which) -> {
                            Toast.makeText(getContext(), "Register Cancelled!", Toast.LENGTH_SHORT).show();
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        return view;
    }
}