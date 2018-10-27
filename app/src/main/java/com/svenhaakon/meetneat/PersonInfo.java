package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class PersonInfo extends Activity {

    //XML fields
    private EditText per_info_name, per_info_phone;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);

        //Set the top toolbar as the actionbar
        Toolbar personAdderToolbar = findViewById(R.id.personinfo_toolbar);
        setActionBar(personAdderToolbar);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);

        //Define XML fields
        per_info_name = findViewById(R.id.per_info_name);
        per_info_phone = findViewById(R.id.per_info_phone);

        //Define Database adder
        db = new DbHandler(this);

        per_info_name.setText(getIntent().getStringExtra("PerName"));
        per_info_phone.setText(getIntent().getStringExtra("PerPhone"));

    }



    public boolean onSupportNavigateUp(){
        finish();
        return true;
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
                updatePerson();
                break;
            case R.id.menu_delete:
                deletePerson();
                break;
            case android.R.id.home:
                onSupportNavigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void updatePerson() {
        Person person = new Person();
        person.set_ID(getIntent().getLongExtra("PerID", 0));
        person.setName(per_info_name.getText().toString());
        person.setPhone(per_info_phone.getText().toString());
        db.updatePerson(person);

        PersonHandler.hasAdded = true;
        Toast.makeText(this, "Updated person", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void deletePerson() {
        Long perId = getIntent().getLongExtra("PerID", 0);
        db.deletePerson(perId);

        //Deleting all reservations the person was in
        for(Reservation res : db.getAllReservations()){
            if(res.getPerson_ID() == perId){
                db.deleteReservation(res.get_ID());
                Main.hasDeleted = true;
            }
        }
        PersonHandler.hasAdded = true;
        Toast.makeText(this, "Deleted person", Toast.LENGTH_SHORT).show();
        finish();
    }
}