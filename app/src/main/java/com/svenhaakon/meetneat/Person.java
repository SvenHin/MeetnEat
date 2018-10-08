package com.svenhaakon.meetneat;

import android.util.Log;

public class Person {
    private long _ID;
    private String name;
    private String phone;

    public Person(long ID, String name, String phone) {

        this._ID = ID;
        this.name = name;
        this.phone = phone;
    }

    public Person(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    public Person() {

    }

    public void set_ID(long ID) {
        this._ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long get_ID() {
        return _ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
