package com.richard.fortuneteller;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NumbersActivity extends AppCompatActivity {

    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_numbers);
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

    }



    public void numberClick(View v) {
        Intent fortuneIntent = new Intent(this, FortuneActivity.class);

        fortuneIntent.putExtra("color", color);

        switch(v.getId()) {
            case R.id.button1:
                fortuneIntent.putExtra("number", "1");
                break;
            case R.id.button2:
                fortuneIntent.putExtra("number", "2");
                break;
            case R.id.button3:
                fortuneIntent.putExtra("number", "3");
                break;
            case R.id.button4:
                fortuneIntent.putExtra("number", "4");
                break;
            case R.id.button5:
                fortuneIntent.putExtra("number", "5");
                break;
            case R.id.button6:
                fortuneIntent.putExtra("number", "6");
                break;
            case R.id.button7:
                fortuneIntent.putExtra("number", "7");
                break;
            case R.id.button8:
                fortuneIntent.putExtra("number", "8");
                break;
        }

        startActivity(fortuneIntent);

    }



}
