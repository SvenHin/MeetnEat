package com.svenhaakon.meetneat;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
    EditText nameIn, phoneIn;
    TextView print;
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameIn = (EditText) findViewById(R.id.name);
        phoneIn = (EditText) findViewById(R.id.phone);
        db = new DbHandler(this);
    }

    public void add(View v){
        Friend friend = new Friend(nameIn.getText().toString(),phoneIn.getText().toString());
        db.addFriend(friend);
    }


}