package com.svenhaakon.meetneat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class RestaurantInfo extends Activity {


    //XML fields
    private TextView rest_name, rest_address, rest_phone, rest_type ;
    private EditText rest_info_name, rest_info_address, rest_info_phone, rest_info_type;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantinfo);

        //Set the top toolbar as the actionbar
        Toolbar restaurantToolbar = findViewById(R.id.restaurantinfo_toolbar);
        setActionBar(restaurantToolbar);

        //Define XML fields
        rest_name = findViewById(R.id.rest_name);
        rest_address = findViewById(R.id.rest_address);
        rest_phone = findViewById(R.id.rest_phone);
        rest_type = findViewById(R.id.rest_type);
        rest_info_name = findViewById(R.id.rest_info_name);
        rest_info_address = findViewById(R.id.rest_info_address);
        rest_info_phone = findViewById(R.id.rest_info_phone);
        rest_info_type = findViewById(R.id.rest_info_type);

        //Define Database adder
        db = new DbHandler(this);

        rest_info_name.setText(getIntent().getStringExtra("RestName"));
        rest_info_address.setText(getIntent().getStringExtra("RestAddress"));
        rest_info_phone.setText(getIntent().getStringExtra("RestPhone"));
        rest_info_type.setText(getIntent().getStringExtra("RestType"));

    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.reservationinfo_menu, menu);

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

    public void updateRestaurant(View v) {
        Restaurant restaurant = new Restaurant();
        restaurant.set_ID(getIntent().getLongExtra("RestID", 0));
        restaurant.setName(rest_info_name.getText().toString());
        restaurant.setAdress(rest_info_address.getText().toString());
        restaurant.setPhone(rest_info_phone.getText().toString());
        restaurant.setType(rest_info_type.getText().toString());
        db.updateRestaurant(restaurant);
    }
}