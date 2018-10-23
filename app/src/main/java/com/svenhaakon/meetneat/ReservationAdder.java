package com.svenhaakon.meetneat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class ReservationAdder extends Activity {

    static boolean hasAdded = false;

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

        //Define XML fields
        res_add_name = findViewById(R.id.res_add_name);
        res_add_date = findViewById(R.id.res_add_date);
        res_add_time = findViewById(R.id.res_add_time);
        res_add_people = findViewById(R.id.res_add_people);

        //Define Database adder
        db = new DbHandler(this);



    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.reservationadder_menu, menu);

        //Define and inflate bottom toolbar
        Toolbar navToolbar = findViewById(R.id.nav_toolbar);
        Menu bottomMenu = navToolbar.getMenu();
        getMenuInflater().inflate(R.menu.nav_menu, bottomMenu);

        //Include items in bottom toolbar in onOptionsItemSelected
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
            case R.id.menu_appointments:
                break;
            case R.id.menu_restaurants:
                break;
            case R.id.menu_people:
                break;
            case R.id.menu_settings:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    //Adds a reservation to the databse from the input fields
    public void addReservation(View v){
        Reservation reservation = new Reservation(Integer.valueOf(res_add_name.getText().toString()),Integer.valueOf(res_add_people.getText().toString()),res_add_date.getText().toString(),res_add_time.getText().toString());
        db.addReservation(reservation);
        hasAdded = true;
        Toast.makeText(getApplicationContext(), "Reservation created",   Toast.LENGTH_LONG).show();
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