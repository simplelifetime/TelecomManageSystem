package com.DatabasePrinciple.TelecomManageSystem.model;

/**
 * 客服
 */
public class CustomService {
    private int id;
    private String fullname;
    private String password;
    private String email;


    public CustomService(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public CustomService(int id, String fullname, String password, String email) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }

    public CustomService(String fullname, String password, String email) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }

    public CustomService(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
