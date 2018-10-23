package com.svenhaakon.meetneat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class PersonInfo extends Activity {


    //XML fields
    private TextView per_name, per_phone;
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

        //Define XML fields
        per_name = findViewById(R.id.per_name);
        per_phone = findViewById(R.id.per_phone);
        per_info_name = findViewById(R.id.per_info_name);
        per_info_phone = findViewById(R.id.per_info_phone);

        //Define Database adder
        db = new DbHandler(this);

        per_info_name.setText(getIntent().getStringExtra("PerName"));
        per_info_phone.setText(getIntent().getStringExtra("PerPhone"));

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

    public void updatePerson(View v) {
        Person person = new Person();
        person.set_ID(getIntent().getLongExtra("PerID", 0));
        person.setName(per_info_name.getText().toString());
        person.setPhone(per_info_phone.getText().toString());
        db.updatePerson(person);
    }
}