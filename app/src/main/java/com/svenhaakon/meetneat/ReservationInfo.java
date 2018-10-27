package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class ReservationInfo extends Activity {


    //XML fields
    private TextView res_name, res_date, res_time, res_people ;
    private EditText res_info_name, res_info_date, res_info_time, res_info_people;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationinfo);

        //Set the top toolbar as the actionbar
        Toolbar reservationAdderToolbar = findViewById(R.id.reservationinfo_toolbar);
        setActionBar(reservationAdderToolbar);

        //Define XML fields
        res_name = findViewById(R.id.res_name);
        res_date = findViewById(R.id.res_date);
        res_time = findViewById(R.id.res_time);
        res_people = findViewById(R.id.res_people);
        res_info_name = findViewById(R.id.res_info_name);
        res_info_date = findViewById(R.id.res_info_date);
        res_info_time = findViewById(R.id.res_info_time);
        res_info_people = findViewById(R.id.res_info_people);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);

        //Define Database adder
        db = new DbHandler(this);

        String restname = db.findRestaurant((int)getIntent().getLongExtra("RestID", 0)).getName();
        res_info_name.setText(restname);
        res_info_date.setText(getIntent().getStringExtra("ResDate"));
        res_info_time.setText(getIntent().getStringExtra("ResTime"));
        res_info_people.setText(db.findPerson((int)getIntent().getLongExtra("PerID", 0)).getName());

    }

    public boolean onSupportNavigateUp(){
        finish();
        return true;
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
            case android.R.id.home:
                onSupportNavigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void updateReservation(View v) {
        Reservation reservation = new Reservation();
        reservation.setRestaurant_ID(db.findRestaurant(Integer.valueOf(res_info_name.getText().toString())).get_ID());
        reservation.set_ID(getIntent().getLongExtra("ResID", 0));
        reservation.setPerson_ID(db.findPerson(Integer.valueOf(res_info_people.getText().toString())).get_ID());

        reservation.setDate(res_info_date.getText().toString());
        reservation.setTime(res_info_time.getText().toString());
        db.updateReservation(reservation);
    }
}