package com.csmht;

public class user {
    public static final user User = new user();
    private static String username;
    private static String password;
    private static String mun;
    
    public String getUsername(){
        return username;
    }
    public void pushUsername(String username) {
        com.csmht.user.username = username;
    }
    public String getPassword(){return password;    }
    public void pushPassword(String password) {
        com.csmht.user.password = password;}

    public String getMun(){
        return mun;
    }
    public void pushMun(String mun){
        com.csmht.user.mun = mun;
    }
}
