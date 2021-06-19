package com.DatabasePrinciple.TelecomManageSystem.model;

/**
 * 用户与用户套餐对应关系
 */
public class own {
    private int id;
    private int uid;
    private int usid;

    public own(int id, int uid, int usid) {
        this.id = id;
        this.uid = uid;
        this.usid = usid;
    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getUsid() {
        return usid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsid(int usid) {
        this.usid = usid;
    }
}
