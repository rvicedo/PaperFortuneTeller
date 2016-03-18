package com.richard.fortuneteller;


public class Fortune {

    private int _id;
    private String number;
    private String description;


    public Fortune() {
        //initialize defaults? if this would ever be used..
    }

    public Fortune(int id, String number, String description) {
        this._id = id;
        this.number = number;
        this.description = description;
    }


    public int getId() {
        return this._id;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDescription() {
        return this.description;
    }


    public void setId(int id) {
        this._id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
