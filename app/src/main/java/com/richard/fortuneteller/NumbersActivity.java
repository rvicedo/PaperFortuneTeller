package com.richard.fortuneteller;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class NumbersActivity extends AppCompatActivity {

    String color;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_numbers);

        //references to buttons
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        Bundle data = getIntent().getExtras();
        if (data == null) {
            return;
        }
        color = data.getString("color", "red");


        //randomly assigning different numbers 1-8 to the 4 buttons
        Random r = new Random();
        int num1, num2, num3, num4;

        num1 = r.nextInt(8) + 1; //random int from 1-8
        button1.setText(Integer.toString(num1));

        num2 = r.nextInt(8) + 1;
        while (num1 == num2) { //dont want duplicate numbers
            num2 = r.nextInt(8) + 1;
        }
        button2.setText(Integer.toString(num2));

        num3 = r.nextInt(8) + 1;
        while (num3 == num1 || num3 == num2) {
            num3 = r.nextInt(8) + 1;
        }
        button3.setText(Integer.toString(num3));

        num4 = r.nextInt(8) + 1;
        while (num4 == num3 || num4 == num2 || num4 == num1) {
            num4 = r.nextInt(8) + 1;
        }
        button4.setText(Integer.toString(num4));


        //no longer dependent on the color choice, but also no longer predictable based on the color choice
    }



    public void numberClick(View v) {
        Intent fortuneIntent = new Intent(this, FortuneActivity.class);

        fortuneIntent.putExtra("color", color); //unneeded now


        switch(v.getId()) { //get the actual number from the text of the Button that was clicked
            case R.id.button1:
                fortuneIntent.putExtra("number", button1.getText().toString());
                break;
            case R.id.button2:
                fortuneIntent.putExtra("number", button2.getText().toString());
                break;
            case R.id.button3:
                fortuneIntent.putExtra("number", button3.getText().toString());
                break;
            case R.id.button4:
                fortuneIntent.putExtra("number", button4.getText().toString());
                break;
        }

        startActivity(fortuneIntent);

    }



}
