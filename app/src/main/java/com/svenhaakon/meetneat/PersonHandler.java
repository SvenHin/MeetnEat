package com.svenhaakon.meetneat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends Activity {
    public static boolean hasAdded = false;
    //XML fields
    ListView peopleList;

    //Database variable
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personhandler);

        //Set the top toolbar as the actionbar
        Toolbar personhandlerToolbar = findViewById(R.id.personhandler_toolbar);
        setActionBar(personhandlerToolbar);

        //HomeButton
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.baseline_arrow_back_white_36dp);



        //Define XML fields
        peopleList = findViewById(R.id.listViewPeople);

        //Define Database handler
        db = new DbHandler(this);

        //Will populate the listview with people from Person table
        populateList();


    }

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    //Refreshes view if person has been added to database
    @Override
    protected void onResume() {
        super.onResume();
        if(hasAdded){
            hasAdded = false;
            this.recreate();
        }
    }

    /** ListView initializer **/
    public void populateList(){

        //Define an arraylist and copy all names from a list from database
        final ArrayList<String> nameList = new ArrayList<>();
        final List<Person> list = db.getAllPeople();
        for(Person person : list){
            nameList.add(person.getName());
        }

        //ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nameList);
        peopleList.setAdapter(arrayAdapter);

        //Put an onclick event on each element in list
        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of which element is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Person person = list.get(position);
                Intent intent = new Intent(PersonHandler.this, PersonInfo.class);
                intent.putExtra("PerID", person.get_ID());
                intent.putExtra("PerName", person.getName());
                intent.putExtra("PerPhone", person.getPhone());
                PersonHandler.this.startActivity(intent);
            }
        });

    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent e = new Intent(this, PersonAdder.class);
                this.startActivity(e);
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