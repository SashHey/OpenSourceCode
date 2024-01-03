package com.example.opsc_moviefans;

import java.util.Date;

public class Collectable {

    public String name;
    public String description;
    public String genre;
    public String dateOfAquisition;

    public Collectable(String name){

        this.name = name;
    }

    public Collectable(String name, String description){

        this.name = name;
        this.description = description;
    }

    public Collectable(String name, String description, String genre){

        this.name = name;
        this.description = description;
        this.genre = genre;
    }

    public Collectable(String name, String description, String genre, String dateOfAquisition){

        this.name = name;
        this.description = description;
        this.genre = genre;
        this.dateOfAquisition = dateOfAquisition;
    }

    public String toString(){

        String newString;

        newString = "Collection: " + name + "\n" +
                "Description: " + description + "\n" +
                "Genre: " + genre + "\n" +
                "Date Aquired: " + dateOfAquisition;

        return newString;
    }

}
