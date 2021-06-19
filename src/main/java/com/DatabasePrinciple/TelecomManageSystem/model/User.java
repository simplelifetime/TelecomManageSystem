package com.DatabasePrinciple.TelecomManageSystem.model;

/**
 * 用户
 */
public class User {
    private Integer id;
    private String fullname;
    private String password;
    private String email;
    private float account;

    public User(Integer userId) {
        this.id = userId;
    }

    public User(String email) {
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String fullname, String password, String email, float account) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.account = account;
    }

    public User(Integer id, String fullname, String password, String email, float account) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.account = account;
    }

    public Integer getId() {
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

    public float getAccount() {
        return account;
    }

    public void setId(Integer id) {
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

    public void setAccount(float account) {
        this.account = account;
    }

    @Override
    public String toString(){
        return "id:" + this.id + " fullname:" + this.fullname + " email:" + this.email
                + " account:" + this.account;
    }
}
