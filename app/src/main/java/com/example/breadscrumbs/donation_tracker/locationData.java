package com.example.breadscrumbs.donation_tracker;

public class locationData extends Throwable {

    private String name;
    private int key;


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String toString() {
        return "LocationData{ " +
                "key= " + key + " " +
                "name " + name +
                "}" ;
    }
}
