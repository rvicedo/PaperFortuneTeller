package com.richard.fortuneteller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class FortuneActivity extends AppCompatActivity {

    private MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    String color;
    String number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fortune);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


        Bundle data = getIntent().getExtras();
        if (data == null) {
            return;
        }
        color = data.getString("color", "red");
        number = data.getString("number", "1");

        //correlate color+number to an entry in the database
        //could maybe have multiple fortunes associated with color+number
        //and randomly pick from those associated with the color+number if theres multiple? <-
        ArrayList<Fortune> fortunes = dbHandler.getFortune(color, number);
        ArrayList<String> descriptions = new ArrayList<String>();
        for (Fortune fortune : fortunes) {
            descriptions.add(fortune.getDescription());
        }
        Random r = new Random();
        if (descriptions.size() == 0) {
            //well, this shouldnt happen once i add the 32 default fortunes in
        }
        else {
            int randomInt = r.nextInt(descriptions.size());
            String fortuneSelected = descriptions.get(randomInt);

            final TextView fortuneTextView = (TextView) findViewById(R.id.fortuneTextView);
            fortuneTextView.setText(fortuneSelected);
        }

        //final TextView fortuneTextView = (TextView) findViewById(R.id.fortuneTextView);
        //String msg = "color: " + color + ", number: " + number;
        //fortuneTextView.setText(msg);

    }



    public void restartClick(View view) {

        Intent restartIntent = new Intent(this, MainActivity.class);

        startActivity(restartIntent);

    }
}
