package com.example.opsc_moviefans;

public class User {

    String username;
    String password;

    public User(){
        //Empty user
    }

    public User(String username){

        //A user without a password
        this.username = username;
    }

    public User(String username, String password){

        //A fully registered user
        this.username = username;
        this.password = password;
    }
}
