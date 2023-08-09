package com.example.trainingfavemobile.models;

public class User {
    String email, full_name, gender;

    public User(){

    }

    public User(String email, String full_name, String gender) {
        this.email = email;
        this.full_name = full_name;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
