package com.javabycode.springboot.model;

import jakarta.persistence.*;

// import org.hibernate.mapping.List;

@Entity
@Table(name = "users")
public class users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int user_id;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "password_hash")
    private String password_hash;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "registration_time")
    private String registration_time;
    @Column(name = "last_login_time")
    private String last_login_time;

    public users() {
    }

    public users(int user_id, String phone_number, String password_hash, String user_name, String registration_time, String last_login_time) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.password_hash = password_hash;
        this.user_name = user_name;
        this.registration_time = registration_time;
        this.last_login_time = last_login_time;
    }

    // Getters and Setters

    public int getuser_id() {
        return user_id;
    }

    public String getphone_number() {
        return phone_number;
    }

    public String getuser_name() {
        return user_name;
    }

    public String getpassword_hash() {
        return password_hash;
    }

    public String getregistration_time() {
        return registration_time;
    }

    public String getlast_login_time() {
        return last_login_time;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setphone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setpassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setregistration_time(String registration_time) {
        this.registration_time = registration_time;
    }

    public void setlast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }
    
}

// CREATE TABLE users (
//     user_id INT PRIMARY KEY IDENTITY(1,1), -- 使用 IDENTITY 自動遞增
//     phone_number VARCHAR(20) UNIQUE NOT NULL,
//     password_salt VARCHAR(255) NOT NULL,
//     password_hash VARCHAR(255) NOT NULL,
//     user_name VARCHAR(255),
//     registration_time DATETIME,
//     last_login_time DATETIME
// );
