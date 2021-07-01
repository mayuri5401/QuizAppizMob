package com.example.quizmob;


public class User {
    private String name, email, pass, profile, referCode;
    private  long coins=25;
    public User(){

    }
    public  User( long coins){
        this.coins = coins;

    }


    public User(String name, String email, String pwd, String refercode) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.referCode = referCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }
}
