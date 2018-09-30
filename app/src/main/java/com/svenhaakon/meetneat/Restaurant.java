package com.svenhaakon.meetneat;

public class Restaurant {
    long ID;
    String name;
    String adress;
    String phone;
    String type;

    public Restaurant(long ID, String name, String adress, String phone, String type) {

        this.ID = ID;
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

    public void setID(long ID) {
        this.ID = ID;
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

    public long getID() {

        return ID;
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
