package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class RestaurantInfo extends Activity implements DeleteDialog.DialogClickListener{

    //XML fields
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

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);

        //Define XML fields
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

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onYesClick() {
        deleteRestaurant();
    }
    @Override
    public void onNoClick() {
        return;
    }
    public void showDeleteDialog(){
        DialogFragment dialog = new DeleteDialog();
        dialog.show(getFragmentManager(),"Delete");
    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                updateRestaurant();
                break;
            case R.id.menu_delete:
                showDeleteDialog();
                break;
            case android.R.id.home:
                onSupportNavigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void updateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.set_ID(getIntent().getLongExtra("RestID", 0));
        restaurant.setName(rest_info_name.getText().toString());
        restaurant.setAdress(rest_info_address.getText().toString());
        restaurant.setPhone(rest_info_phone.getText().toString());
        restaurant.setType(rest_info_type.getText().toString());
        db.updateRestaurant(restaurant);

        RestaurantHandler.hasAdded = true;
        Main.hasAdded = true;
        Toast.makeText(this, "Updated restaurant", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void deleteRestaurant() {
        Long restId = getIntent().getLongExtra("RestID", 0);
        db.deleteRestaurant(restId);

        //Deleting all reservations the restaurant was in
        for(Reservation res : db.getAllReservations()){
            if(res.getRestaurant_ID() == restId){
                db.deleteReservation(res.get_ID());
                Main.hasDeleted = true;
            }
        }
        RestaurantHandler.hasAdded = true;
        Toast.makeText(this, "Deleted restaurant", Toast.LENGTH_SHORT).show();
        finish();
    }
}