package com.example.housingsociety.Model;

public class FirebaseSignUpModel {
    String username;
    String email;
    String phone;
    String password;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    String uri;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public FirebaseSignUpModel(String username, String email, String phone, String password, String uri) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.uri = uri;

    }
}
