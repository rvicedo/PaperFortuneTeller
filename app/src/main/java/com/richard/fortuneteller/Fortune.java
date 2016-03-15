package com.richard.fortuneteller;


public class Fortune {

    private String _id;
    private String color;
    private String number;
    private String description;


    public Fortune() {
        //initialize defaults? if this would ever be used..
    }

    public Fortune(String id, String color, String number, String description) {
        this._id = id;
        this.color = color;
        this.number = number;
        this.description = description;
    }


    public String getId() {
        return this._id;
    }

    public String getColor() {
        return this.color;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDescription() {
        return this.description;
    }


    public void setId(String id) {
        this._id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
