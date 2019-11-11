package com.example.uberapp;

public class Users {

    private String username;
    private String password;

    private boolean userType; // false = conductor , true = usuario


    public Users(String user, String passwd) {
        this.username = user;
        this.password = passwd;

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


    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }
}
