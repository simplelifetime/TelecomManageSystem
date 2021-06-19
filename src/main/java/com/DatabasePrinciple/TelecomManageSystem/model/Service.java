package com.DatabasePrinciple.TelecomManageSystem.model;

/**
 *  产品服务
 */
public class Service {
    private int id;
    private String fullname;
    private float price;
    private String description;


    public Service(int id, String fullname, float price, String description) {
        this.id = id;
        this.fullname = fullname;
        this.price = price;
        this.description = description;
    }

    public Service(int id, String fullname, float price) {
        this.id = id;
        this.fullname = fullname;
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

}
