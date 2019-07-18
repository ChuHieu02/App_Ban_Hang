package com.example.chuhieu.kiot_demo.Login.model;

public class User {

    private  String token;
    private  String Error;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
