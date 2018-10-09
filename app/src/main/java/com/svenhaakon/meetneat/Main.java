package com.svenhaakon.meetneat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Main extends Activity {
    //XML fields
    ListView reservationList;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the top toolbar as the actionbar
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setActionBar(mainToolbar);

        //Define XML fields
        reservationList = findViewById(R.id.listViewReservations);

        //Define Database handler
        db = new DbHandler(this);

        //Will populate the listview with people from Person table
        populateList();
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
                Intent i = new Intent(this, ReservationAdder.class);
                this.startActivity(i);
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

    /** ListView initializer **/
    public void populateList(){

        //Define an arraylist and copy all names from a list from database
        final ArrayList<String> dateList = new ArrayList<>();
        List<Reservation> list = db.getAllReservations();
        for(Reservation reservation : list){
            dateList.add(reservation.getDate());
        }

        //ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dateList);
        reservationList.setAdapter(arrayAdapter);

        //Put an onclick event on each element in list
        reservationList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of which element is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                String selectedReservation = dateList.get(position);
                Toast.makeText(getApplicationContext(), "Person Selected : " + selectedReservation,   Toast.LENGTH_LONG).show();
            }
        });

    }

    /*public void add(View v){
        //Person person = new Person(nameIn.getText().toString(),phoneIn.getText().toString());

        Reservation reservation = new Reservation(Integer.valueOf(nameIn.getText().toString()),Integer.valueOf(addressIn.getText().toString()),phoneIn.getText().toString(),typeIn.getText().toString());
        db.addReservation(reservation);
    }

    public void listAll(View v) {
        String string = "";
        List<Reservation> reservations = db.getAllReservations();
        for (Reservation reservation : reservations) {
            string = string + "Id: " + reservation.get_ID() + ",Res_Id: " + reservation.getRestaurant_ID() + " ,Per_Id: " + reservation.getPerson_ID() + " ,Date: " + reservation.getDate() + " ,Time: " + reservation.getTime();
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
    }*/


}