package com.example.housingsociety.Model;

public class FirebaseModel {
    String name, phone, fees, imageUrL;

    public String getImageUrL() {
        return imageUrL;
    }

    public void setImageUrL(String imageUrL) {
        this.imageUrL = imageUrL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }
public FirebaseModel(){

}
    public FirebaseModel(String name, String phone, String fees, String imageUrL) {
        this.name = name;
        this.phone = phone;
        this.fees = fees;
        this.imageUrL = imageUrL;
    }
}
