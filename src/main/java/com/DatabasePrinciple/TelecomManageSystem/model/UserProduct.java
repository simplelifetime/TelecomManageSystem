package com.DatabasePrinciple.TelecomManageSystem.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 客户产品
 */
public class UserProduct {
    private int id;
    private Timestamp s_time;
    private Timestamp e_time;
    private int state;
    private int uid;
    private int sid;
    private int autoRecharge;

    public UserProduct(int id, Timestamp s_time, Timestamp e_time, int state, int uid, int sid, int autoRecharge) {
        this.id = id;
        this.s_time = s_time;
        this.e_time = e_time;
        this.state = state;
        this.uid = uid;
        this.sid = sid;
        this.autoRecharge = autoRecharge;
    }

    public UserProduct(Timestamp s_time, Timestamp e_time, int state, int uid, int sid, int autoRecharge) {
        this.s_time = s_time;
        this.e_time = e_time;
        this.state = state;
        this.uid = uid;
        this.sid = sid;
        this.autoRecharge = autoRecharge;
    }

    public int getId() {
        return id;
    }

    public Timestamp getS_time() {
        return s_time;
    }

    public Timestamp getE_time() {
        return e_time;
    }

    public int getState() {
        return state;
    }

    public int getUid() {
        return uid;
    }

    public int getSid() {
        return sid;
    }

    public int getAutoRecharge() {
        return autoRecharge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setS_time(Timestamp s_time) {
        this.s_time = s_time;
    }

    public void setE_time(Timestamp e_time) {
        this.e_time = e_time;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setAutoRecharge(int autoRecharge) {
        this.autoRecharge = autoRecharge;
    }
}
