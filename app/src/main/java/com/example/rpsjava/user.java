package com.example.rpsjava;

public class user {
    private int id;
    private String username;
    private String password;

    public user(){
    }

    public user(String username, String password){
        this.username = username;
        this.password = password;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password = password;
    }

}
