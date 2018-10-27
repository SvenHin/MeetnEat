package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class ReservationAdder extends Activity {
    //XML fields
    private EditText res_add_name, res_add_date, res_add_time, res_add_people;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationadder);

        //Set the top toolbar as the actionbar
        Toolbar reservationAdderToolbar = findViewById(R.id.reservationadder_toolbar);
        setActionBar(reservationAdderToolbar);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);


        //Define XML fields
        res_add_name = findViewById(R.id.res_add_name);
        res_add_date = findViewById(R.id.res_add_date);
        res_add_time = findViewById(R.id.res_add_time);
        res_add_people = findViewById(R.id.res_add_people);

        //Define Database adder
        db = new DbHandler(this);



    }


    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                addReservation();
                break;
            case android.R.id.home:
                onSupportNavigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    //Adds a reservation to the databse from the input fields
    public void addReservation(){
        Reservation reservation = new Reservation();

        //Find id from restaurant name
        boolean foundRest = false;
        for(Restaurant res :db.getAllRestaurants()){
            if(res.getName().equals(res_add_name.getText().toString())){
                reservation.setRestaurant_ID(res.get_ID());
                foundRest = true;
            }
        }

        if(!foundRest){
            Toast.makeText(this, "Could not find restaurant", Toast.LENGTH_SHORT).show();
            return;
        }

        //Find id from friend name
        boolean foundPers = false;
        for(Person person :db.getAllPeople()){
            if(person.getName().equals(res_add_people.getText().toString())){
                reservation.setPerson_ID(person.get_ID());
                foundPers = true;
            }
        }

        if(!foundPers){
            Toast.makeText(this, "Could not find person", Toast.LENGTH_SHORT).show();
            return;
        }
        reservation.set_ID(getIntent().getLongExtra("ResID", 0));
        reservation.setDate(res_add_date.getText().toString());
        reservation.setTime(res_add_time.getText().toString());
        db.updateReservation(reservation);

        Main.hasAdded = true;
        Toast.makeText(this, "Added reservation", Toast.LENGTH_SHORT).show();
        finish();
    }


/*    public void listAll(View v) {
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



    public void numberOfPeople(View v) {
        print.setText(String.valueOf(db.numberOfRestaurants()));
    }

    public void findAPerson(View v){
        Restaurant restaurant = db.findRestaurant(Integer.valueOf(idIn.getText().toString()));
        String string = "Id: " + restaurant.get_ID() + ",Name: " + restaurant.getName() + " ,Address: " + restaurant.getAdress() + " ,Phone: " + restaurant.getPhone() + " ,Type: " + restaurant.getType();
        print.setText(string);
    }
*/

}