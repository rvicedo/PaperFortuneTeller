package com.richard.fortuneteller;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class FortuneActivity extends AppCompatActivity {

    //build the object that is going to be the notification itself
    NotificationCompat.Builder notification;
    private static final int uniqueID = 28542; //the notification has to be assigned a unique ID


    private MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    String color;
    String number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fortune);

        //get the saved color and number extras
        Bundle data = getIntent().getExtras();
        if (data == null) {
            return;
        }
        color = data.getString("color", "red");
        number = data.getString("number", "1");

        //get the fortune descriptions from the database for the number chosen
        ArrayList<Fortune> fortunes = dbHandler.getFortunes(number);
        ArrayList<String> descriptions = new ArrayList<String>();
        for (Fortune f : fortunes) {
            descriptions.add(f.getDescription());
        }

        //reference to the widget
        TextView fortuneTextView = (TextView) findViewById(R.id.fortuneTextView);

        if (descriptions.size() == 0) { //shouldnt happen once i add in the 8 starting fortunes
            fortuneTextView.setText("nothing");
        }
        else { //chooses a random fortune description from the number chosen
            Random r = new Random();
            int randomInt = r.nextInt(descriptions.size());
            String fortuneSelected = descriptions.get(randomInt);

            fortuneTextView.setText(fortuneSelected);
        }


        //build the new notification
        notification = new NotificationCompat.Builder(this);
        //remove notification once it has been visited
        notification.setAutoCancel(true);

    }



    public void restartClick(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


    public void notifyClick(View view) {
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("What is a ticker?");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Congratulations");
        notification.setContentText("You clicked the button!");
        notification.setPriority(NotificationCompat.PRIORITY_MAX);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(alarmSound);

        //send the notification to the home screen
        Intent i = new Intent(this, MainActivity.class);
        //give the device access to perform this intent by calling the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //send out the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

    }


}
