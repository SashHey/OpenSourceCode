package com.example.opsc_moviefans;

import android.util.Log;

import java.util.List;

public class Category {

    public List<Collectable> savedCollectables;

    String name;
    String description;
    int goal;

    public Category(String name){

        this.name = name;
    }

    public Category(String name, String description){

        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, int goal){
        this.name = name;
        this.description = description;
        this.goal = goal;
    }

    public String toString(){

        String newString;

        newString = "Collection: " + name + "\n" +
                 "Description: " + description + "\n" +
                 "Goal: " + goal;

        return newString;
    }

}
