package com.example.housingsociety.Model;

public class Model {
    String email, password;
    int image;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Model(String email, String password, int image) {
        this.email = email;
        this.password = password;
        this.image = image;
    }
}
