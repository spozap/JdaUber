package com.example.uberapp;

public class Users {

    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean userType; // false = conductor , true = usuario


    public Users(String user, String passwd, String mail, String tlf) {
        user = this.username;
        passwd = this.password;
        mail = this.email;
        tlf = this.phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }
}
