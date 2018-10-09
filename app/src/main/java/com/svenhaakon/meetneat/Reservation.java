package com.svenhaakon.meetneat;

public class Reservation {
    private long _ID;
    private long Restaurant_ID;
    private long Person_ID;
    private String date;
    private String time;

    public Reservation(long _ID, long restaurant_ID, long person_ID, String date, String time) {
        this._ID = _ID;
        Restaurant_ID = restaurant_ID;
        Person_ID = person_ID;
        this.date = date;
        this.time = time;
    }

    public Reservation(long restaurant_ID, long person_ID, String date, String time) {

        Restaurant_ID = restaurant_ID;
        Person_ID = person_ID;
        this.date = date;
        this.time = time;
    }

    public Reservation() {

    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setRestaurant_ID(long restaurant_ID) {
        Restaurant_ID = restaurant_ID;
    }

    public void setPerson_ID(long person_ID) {
        Person_ID = person_ID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long get_ID() {
        return _ID;
    }

    public long getRestaurant_ID() {
        return Restaurant_ID;
    }

    public long getPerson_ID() {
        return Person_ID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
