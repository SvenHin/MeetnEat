package com.svenhaakon.meetneat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

public class PersonAdder extends Activity {
    static boolean hasAdded = false;

    //XML fields
    private EditText per_add_name, per_add_phone;

    //Database variable
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personadder);

        //Set the top toolbar as the actionbar
        Toolbar personAdderToolbar = findViewById(R.id.personadder_toolbar);
        setActionBar(personAdderToolbar);

        //Define XML fields
        per_add_name = findViewById(R.id.per_add_name);
        per_add_phone = findViewById(R.id.per_add_phone);


        //Define Database adder
        db = new DbHandler(this);



    }

    /** Menu Methods **/
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate top toolbar
        getMenuInflater().inflate(R.menu.personadder_menu, menu);

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
    public void addPerson(View v){

        //ALARMSTOPPER
        //Intent i = new Intent(this, NotificationService.class);
        //PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        //AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //if(alarm!=null){
        //    alarm.cancel(pintent);
        //}
        //ALARMSTARTER
        //Intent intent = new Intent();
        //intent.setAction("com.svenhaakon.meetneat.broadcast");
        //sendBroadcast(intent);



        Person person = new Person(per_add_name.getText().toString(),per_add_phone.getText().toString());
        db.addPerson(person);
        hasAdded = true;

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