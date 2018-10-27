package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ReservationInfo extends Activity implements DeleteDialog.DialogClickListener{

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

        //Fill fields from intent
        res_info_name.setText(db.findRestaurant((int)getIntent().getLongExtra("RestID", 0)).getName());
        res_info_date.setText(getIntent().getStringExtra("ResDate"));
        res_info_time.setText(getIntent().getStringExtra("ResTime"));
        res_info_people.setText(db.findPerson((int)getIntent().getLongExtra("PerID", 0)).getName());

    }

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onYesClick() {
        deleteReservation();
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
                updateReservation();
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

    public void updateReservation() {
        Reservation reservation = new Reservation();

        //Find id from restaurant name
        boolean foundRest = false;
        for(Restaurant res :db.getAllRestaurants()){
            if(res.getName().equals(res_info_name.getText().toString())){
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
            if(person.getName().equals(res_info_people.getText().toString())){
                reservation.setPerson_ID(person.get_ID());
                foundPers = true;
            }
        }

        if(!foundPers){
            Toast.makeText(this, "Could not find person", Toast.LENGTH_SHORT).show();
            return;
        }
        reservation.set_ID(getIntent().getLongExtra("ResID", 0));
        reservation.setDate(res_info_date.getText().toString());
        reservation.setTime(res_info_time.getText().toString());
        db.updateReservation(reservation);

        Main.hasAdded = true;
        Toast.makeText(this, "Updated reservation", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void deleteReservation() {
        Long resId = getIntent().getLongExtra("ResID", 0);
        db.deleteReservation(resId);

        Main.hasAdded = true;
        Toast.makeText(this, "Deleted reservation", Toast.LENGTH_SHORT).show();
        finish();
    }
}