package com.richard.fortuneteller;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    //database variables
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "fortunes.db";
    private static final String TABLE_FORTUNES = "fortunes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_DESCRIPTION = "description";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FORTUNES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER + " TEXT, " + COLUMN_DESCRIPTION + " TEXT );";
        db.execSQL(query);

        //default starting fortunes for each number
        addFortune(db, "1", "You buy a lotto ticket and win and spend the rest of your life living in paradise but you suddenly wake up in a psych ward and remember you have life-crippling psychosis");
        addFortune(db, "2", "You might die someday");
        addFortune(db, "3", "You will get an A on your next exam");
        addFortune(db, "4", "Your crush will call you tomorrow");
        addFortune(db, "5", "You meet leonardo dicaprio on a hike in canada but he wont take a selfie with you and rambles on about climate change for hours on end");
        addFortune(db, "6", "You will live an extremely average life");
        addFortune(db, "7", "You end up on a deserted island with only donald trump and a leaf blower");
        addFortune(db, "8", "You will win the next competition you enter");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORTUNES);
        onCreate(db);
    }


    //adds a Fortune with a number and description to the database
    public void addFortune(SQLiteDatabase db, String number, String description) {
        String query = "INSERT INTO " + TABLE_FORTUNES + " ( " + COLUMN_ID + ", " + COLUMN_NUMBER + ", " +
                COLUMN_DESCRIPTION + " ) " + " VALUES ( " + "NULL" + ", " + "\'" + number + "\'" + ", " +
                "\'" + description + "\'" + " ) ";
        db.execSQL(query);

    }



    public ArrayList<Fortune> getFortunes(String number) {
        ArrayList<Fortune> fortunes = new ArrayList<Fortune>();
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_FORTUNES + " WHERE " +
                COLUMN_NUMBER + " = " + number;

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);

        //move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Fortune fortune = new Fortune(c.getInt(0), c.getString(1), c.getString(2));
            fortunes.add(fortune);
            c.moveToNext();
        }

        db.close();
        return fortunes;
    }


}
