package com.example.uberapp;

public class Users {

    private String username;
    private String password;
    private String rol;

    public Users(String user, String passwd, String rol) {
        this.username = user;
        this.password = passwd;
        this.rol = rol;

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
