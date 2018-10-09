package com.svenhaakon.meetneat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

public class Main extends Activity {
    EditText nameIn, addressIn, phoneIn, typeIn, idIn;
    TextView print;
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setActionBar(mainToolbar);

        nameIn = findViewById(R.id.name);
        phoneIn = findViewById(R.id.phone);
        addressIn = findViewById(R.id.address);
        print = findViewById(R.id.view);



        db = new DbHandler(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //Inflate bottom toolbar
        Toolbar navToolbar = findViewById(R.id.nav_toolbar);
        Menu bottomMenu = navToolbar.getMenu();
        getMenuInflater().inflate(R.menu.nav_menu, bottomMenu);

        for (int i = 0; i < bottomMenu.size(); i++) {
            bottomMenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return onOptionsItemSelected(item);
                }
            });
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                break;
            case R.id.menu_appointments:
                break;
            case R.id.menu_restaurants:
                break;
            case R.id.menu_people:
                Intent e = new Intent(this, PersonHandler.class);
                this.startActivity(e);
                break;
            case R.id.menu_settings:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void add(View v){
        Person person = new Person(nameIn.getText().toString(),phoneIn.getText().toString());
        db.addPerson(person);
    }

    public void listAll(View v) {
        String string = "";
        List<Restaurant> restaurants = db.getAllRestaurants();
        for (Restaurant restaurant : restaurants) {
            string = string + "Id: " + restaurant.get_ID() + ",Name: " + restaurant.getName() + " ,Address: " + restaurant.getAdress() + " ,Phone: " + restaurant.getPhone() + " ,Type: " + restaurant.getType();
            Log.d("Name: ", string);
        }
        print.setText(string);
    }

    public void delete(View v) {
        Long personId = (Long.parseLong(idIn.getText().toString()));
        db.deleteRestaurant(personId);
    }

    public void update(View v) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(nameIn.getText().toString());
        restaurant.setAdress(addressIn.getText().toString());
        restaurant.setPhone(phoneIn.getText().toString());
        restaurant.setType(typeIn.getText().toString());
        restaurant.set_ID(Long.parseLong(idIn.getText().toString()));
        db.updateRestaurant(restaurant);
    }

    public void numberOfPeople(View v) {
        print.setText(String.valueOf(db.numberOfRestaurants()));
    }

    public void findAPerson(View v){
        Restaurant restaurant = db.findRestaurant(Integer.valueOf(idIn.getText().toString()));
        String string = "Id: " + restaurant.get_ID() + ",Name: " + restaurant.getName() + " ,Address: " + restaurant.getAdress() + " ,Phone: " + restaurant.getPhone() + " ,Type: " + restaurant.getType();
        print.setText(string);
    }


}