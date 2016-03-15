package com.richard.fortuneteller;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ColorsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_colors);
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
    }



    public void colorClick(View v) {
        Intent numbersIntent = new Intent(this, NumbersActivity.class);

        switch(v.getId()) {
            case R.id.redButton:
                numbersIntent.putExtra("color", "red");
                break;
            case R.id.yellowButton:
                numbersIntent.putExtra("color", "yellow");
                break;
            case R.id.blueButton:
                numbersIntent.putExtra("color", "blue");
                break;
            case R.id.greenButton:
                numbersIntent.putExtra("color", "green");
                break;
        }

        startActivity(numbersIntent);

    }




}
