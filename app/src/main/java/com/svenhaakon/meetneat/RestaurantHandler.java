package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class RestaurantHandler extends Activity {
    //XML fields
    ListView restaurantList;

    //Database variable
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restauranthandler);

        //Set the top toolbar as the actionbar
        Toolbar restauranthandlerToolbar = findViewById(R.id.restauranthandler_toolbar);
        setActionBar(restauranthandlerToolbar);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);

        //Define XML fields
        restaurantList = findViewById(R.id.listViewRestaurants);

        //Define Database handler
        db = new DbHandler(this);

        //Will populate the listview with people from Restaurant table
        populateList();


    }

    //Refreshes view if restaurant has been added to database
    @Override
    protected void onResume() {
        super.onResume();
        if(RestaurantAdder.hasAdded){
            RestaurantAdder.hasAdded = false;
            this.recreate();
        }
    }

    /** ListView initializer **/
    public void populateList(){

        //Define an arraylist and copy all names from a list from database
        final ArrayList<String> nameList = new ArrayList<>();
        final List<Restaurant> list = db.getAllRestaurants();
        for(Restaurant restaurant : list){
            nameList.add(restaurant.getName());
        }

        //ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nameList);
        restaurantList.setAdapter(arrayAdapter);

        //Put an onclick event on each element in list
        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of which element is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Restaurant restaurant = list.get(position);
                Intent intent = new Intent(RestaurantHandler.this, RestaurantInfo.class);
                intent.putExtra("RestID", restaurant.get_ID());
                intent.putExtra("RestName", restaurant.getName());
                intent.putExtra("RestAddress", restaurant.getAdress());
                intent.putExtra("RestPhone", restaurant.getPhone());
                intent.putExtra("RestType", restaurant.getType());
                RestaurantHandler.this.startActivity(intent);
            }
        });

    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.restauranthandler_menu, menu);

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
            case R.id.menu_addrestaurant:
                Intent e = new Intent(this, RestaurantAdder.class);
                this.startActivity(e);
                break;
            case R.id.menu_appointments:
                break;
            case R.id.menu_restaurants:
                break;
            case R.id.menu_people:
                break;
            case R.id.menu_settings:
                break;
            case android.R.id.home:
                onSupportNavigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



    /*public void add(View v){
        Restaurant restaurant = new Restaurant(nameIn.getText().toString(),addressIn.getText().toString(),phoneIn.getText().toString(), typeIn.getText().toString());
        db.addRestaurant(restaurant);
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

    */
}