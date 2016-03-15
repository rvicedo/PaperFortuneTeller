package com.richard.fortuneteller;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    //database variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fortunes.db";
    private static final String TABLE_FORTUNES = "fortunes";
    private static final String COLUMN_ID = "_id"; //need for multiple number+color associations
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_DESCRIPTION = "description";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FORTUNES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COLOR + " TEXT, " + COLUMN_NUMBER + " TEXT, " + COLUMN_DESCRIPTION + " TEXT );";
        db.execSQL(query);

        String fortune1 = "INSERT INTO " + TABLE_FORTUNES + " ( " + COLUMN_ID + ", " + COLUMN_COLOR + ", " +
                COLUMN_NUMBER + ", " + COLUMN_DESCRIPTION + " ) " + " VALUES ( " + "1" + ", 'red', " + "1" +
                ", 'you will die' )"; //could make helper method to simplify this process to just a method call with parameters
        db.execSQL(fortune1);

        //need to insert the default/starting/base 4*8=32 fortunes here...

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORTUNES);
        onCreate(db);
    }





    public ArrayList<Fortune> getFortune(String color, String number) {
        ArrayList<Fortune> fortunes = new ArrayList<Fortune>();
        SQLiteDatabase db = getWritableDatabase();
        //String query = "SELECT * FROM " + TABLE_FORTUNES + " WHERE 1";
        String query1 = "SELECT * FROM " + TABLE_FORTUNES + " WHERE " +
                COLUMN_COLOR + " = \'" + color + "\' AND " + COLUMN_NUMBER + " = " + number;

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query1, null);

        //move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Fortune fortune = cursorToFortune(c);
            fortunes.add(fortune);
            c.moveToNext();
        }

        db.close();
        return fortunes;
    }








    public ArrayList<Fortune> getFortunes() {
        ArrayList<Fortune> fortunes = new ArrayList<Fortune>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FORTUNES + " WHERE 1";

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);

        //move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Fortune fortune = cursorToFortune(c);
            fortunes.add(fortune);
            c.moveToNext();
        }

        db.close();
        return fortunes;
    }


    private Fortune cursorToFortune(Cursor c) {
        Fortune fortune = new Fortune();
        fortune.setId(c.getString(0));
        fortune.setColor(c.getString(1));
        fortune.setNumber(c.getString(2));
        fortune.setDescription(c.getString(3));
        return fortune;
    }


}
