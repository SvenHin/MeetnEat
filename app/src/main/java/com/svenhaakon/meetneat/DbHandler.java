package com.svenhaakon.meetneat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    //Database
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "DB";

    //Table names
    private static String TABLE_PERSON = "Person";
    private static String TABLE_RESTAURANT = "Restaurant";

    //Common column names
    private static String KEY_ID = "_ID";
    private static String KEY_PH_NO = "Phone";
    private static String KEY_NAME = "Name";

    //Restaurant Table - Column names
    private static String KEY_TYPE = "Type";
    private static String KEY_ADDRESS = "Address";

    //Person Table - Create Statement
    private static String CREATE_TABLE_PERSON = "CREATE TABLE " + TABLE_PERSON + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";

    //Restaurant Table - Create Statement
    private static String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_PH_NO + " TEXT," + KEY_TYPE + " TEXT" + ")";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_RESTAURANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        onCreate(db);
    }
    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_PH_NO, person.getPhone());
        db.insert(TABLE_PERSON, null, values);
        db.close();
    }

    public void addRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_ADDRESS, restaurant.getAdress());
        values.put(KEY_PH_NO, restaurant.getPhone());
        values.put(KEY_TYPE, restaurant.getType());
        db.insert(TABLE_RESTAURANT, null, values);
        db.close();
    }

    public List<Person> getAllPeople() {
        List <Person> peopleList = new ArrayList<Person>();
        String selectQuery = "SELECT * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.set_ID(cursor.getLong(0));
                person.setName(cursor.getString(1));
                person.setPhone(cursor.getString(2));
                peopleList.add(person);
            } while(cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return peopleList;
    }

    public List<Restaurant> getAllRestaurants() {
        List <Restaurant> restaurantList = new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getLong(0));
                restaurant.setName(cursor.getString(1));
                restaurant.setAdress(cursor.getString(2));
                restaurant.setPhone(cursor.getString(3));
                restaurant.setType(cursor.getString(4));
                restaurantList.add(restaurant);
            } while(cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return restaurantList;
    }

    public void deletePerson(Long in_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSON, KEY_ID + " =? ",
                new String[]{Long.toString(in_id)});
        db.close();
    }

    public void deleteRestaurant(Long in_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANT, KEY_ID + " =? ",
                new String[]{Long.toString(in_id)});
        db.close();
    }

    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_PH_NO, person.getPhone());
        int changed = db.update(TABLE_PERSON, values, KEY_ID + "= ?",
                new String[]{String.valueOf(person.get_ID())});
        db.close();
        return changed;
    }

    public int updateRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_ADDRESS, restaurant.getAdress());
        values.put(KEY_PH_NO, restaurant.getPhone());
        values.put(KEY_TYPE, restaurant.getType());
        int changed = db.update(TABLE_RESTAURANT, values, KEY_ID + "= ?",
                new String[]{String.valueOf(restaurant.get_ID())});
        db.close();
        return changed;
    }

    public int numberOfPeople() {
        String sql = "SELECT * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int number = cursor.getCount();
        cursor.close();
        db.close();
        return number;
    }

    public int numberOfRestaurants() {
        String sql = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int number = cursor.getCount();
        cursor.close();
        db.close();
        return number;
    }

    public Person findPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PERSON, new String[]{
                KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);
        if(cursor!= null) cursor.moveToFirst();
        Person person = new Person(Long.parseLong(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return person;
    }

    public Restaurant findRestaurant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANT, new String[]{
                KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PH_NO, KEY_TYPE}, KEY_ID + "=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);
        if(cursor!= null) cursor.moveToFirst();
        Restaurant restaurant = new Restaurant(Long.parseLong(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        cursor.close();
        db.close();
        return restaurant;
    }
}
