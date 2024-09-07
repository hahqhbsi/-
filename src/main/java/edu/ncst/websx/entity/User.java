package edu.ncst.websx.entity;

public class User {
    private String user_password;
    private Character user_id;

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Character getUser_id() {
        return user_id;
    }

    public void setUser_id(Character user_id) {
        this.user_id = user_id;
    }
}
