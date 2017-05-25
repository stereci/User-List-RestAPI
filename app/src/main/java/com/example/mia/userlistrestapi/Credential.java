package com.example.mia.userlistrestapi;


public class Credential {
    private String UserName;
    private String Password;
    private static Credential instance;

    public static Credential getInstance() { return instance; }

    public static void setInstance(Credential instance) { Credential.instance = instance; }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() { return Password; }

    public void setPassword(String password) { Password = password; }
}
