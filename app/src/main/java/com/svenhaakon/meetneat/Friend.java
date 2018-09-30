package com.svenhaakon.meetneat;

import android.util.Log;

public class Friend {
    long ID;
    String name;
    String phone;

    public Friend(long ID, String name, String phone) {

        this.ID = ID;
        this.name = name;
        this.phone = phone;
    }

    public Friend(String name, String phone) {

        this.name = name;
        this.phone = phone;
        Log.d("Friend constructor", "Name: " + name + " Phone: " + phone);
    }

    public Friend() {

    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }

}
