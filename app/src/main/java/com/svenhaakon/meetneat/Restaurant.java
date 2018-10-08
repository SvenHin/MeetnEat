package com.svenhaakon.meetneat;

public class Restaurant {
    private long _ID;
    private String name;
    private String adress;
    private String phone;
    private String type;

    public Restaurant(String name, String adress, String phone, String type) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.type = type;
    }

    public Restaurant(long _ID, String name, String adress, String phone, String type) {

        this._ID = _ID;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.type = type;
    }

    public Restaurant(String name, String adress, String phone) {

        this.name = name;
        this.adress = adress;
        this.phone = phone;
    }

    public Restaurant(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    public Restaurant() {

    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long get_ID() {

        return _ID;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }
}
