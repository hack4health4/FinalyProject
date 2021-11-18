package com.example.loginregister;

public class Nurse {
    private String fullname, email, gender, date, password, placement,phone, photo;
    Nurse(){}
    Nurse(String email, String subject, String phone ){
        this.email = email;
        this.fullname = subject;
        this.phone = phone;
    }
    Nurse(String fullname, String email, String gender, String date, String password, String placement,String phone, String photo){
        this.fullname = fullname;
        this.email=email; this.gender=gender;
        this.date = date;
        this.password = password;
        this.placement = placement;
        this.phone = phone;
        this.photo = photo;
    }


}
