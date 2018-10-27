package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class RestaurantAdder extends Activity {

    //XML fields
    private EditText rest_add_name, rest_add_type, rest_add_address, rest_add_phone;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantadder);

        //Set the top toolbar as the actionbar
        Toolbar reservationAdderToolbar = findViewById(R.id.restaurantadder_toolbar);
        setActionBar(reservationAdderToolbar);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);

        //Define XML fields
        rest_add_name = findViewById(R.id.rest_add_name);
        rest_add_type = findViewById(R.id.rest_add_type);
        rest_add_address = findViewById(R.id.rest_add_address);
        rest_add_phone = findViewById(R.id.rest_add_phone);

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
                addRestaurant();
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
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant(rest_add_name.getText().toString(),rest_add_address.getText().toString(),rest_add_phone.getText().toString(),rest_add_type.getText().toString());
        db.addRestaurant(restaurant);

        RestaurantHandler.hasAdded = true;
        Toast.makeText(this, "Added restaurant", Toast.LENGTH_SHORT).show();
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
*/

}