package com.richard.fortuneteller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_colors);

        //getting references to the buttons
        Button redButton = (Button) findViewById(R.id.redButton); //should change names of buttons
        Button yellowButton = (Button) findViewById(R.id.yellowButton);
        Button blueButton = (Button) findViewById(R.id.blueButton);
        Button greenButton = (Button) findViewById(R.id.greenButton);


        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("yellow");
        colors.add("blue");
        colors.add("green");
        //could add more colors probably

        Random r = new Random();
        String color1, color2, color3, color4;

        color1 = colors.get(r.nextInt(colors.size())); //picks a random index from the colors arraylist
        redButton.setText(color1);

        color2 = colors.get(r.nextInt(colors.size()));
        while (color1.equals(color2)) { //makes sure there arent duplicate colors picked
            color2 = colors.get(r.nextInt(colors.size()));
        }
        yellowButton.setText(color2);

        color3 = colors.get(r.nextInt(colors.size()));
        while (color1.equals(color3) || color2.equals(color3)) {
            color3 = colors.get(r.nextInt(colors.size()));
        }
        blueButton.setText(color3);

        color4 = colors.get(r.nextInt(colors.size()));
        while (color1.equals(color4) || color2.equals(color4) || color3.equals(color4)) {
            color4 = colors.get(r.nextInt(colors.size()));
        }
        greenButton.setText(color4);


    }


    public void colorClick(View v) {
        Intent numbersIntent = new Intent(this, NumbersActivity.class);

        //not actually used anymore, but could change it to dynamically get the text from the button that was clicked
        // like the numbers activity does
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
