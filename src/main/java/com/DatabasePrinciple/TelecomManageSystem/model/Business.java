package com.DatabasePrinciple.TelecomManageSystem.model;

/**
 * description:
 * author: jason
 **/
public class Business {
    private int id;
    private int uid;
    private int cid;
    private int upid;
    private int state;

    public Business(int id, int uid, int cid, int upid, int state) {
        this.id = id;
        this.uid = uid;
        this.cid = cid;
        this.upid = upid;
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setUpid(int upid) {
        this.upid = upid;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getCid() {
        return cid;
    }

    public int getUpid() {
        return upid;
    }

    public int getState() {
        return state;
    }





}
