package com.example.breadscrumbs.donation_tracker;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SimpleModel {
    public static final SimpleModel INSTANCE = new SimpleModel();

    private List<locationData> items;

    private SimpleModel() {
        items = new ArrayList<>();
    }

    public void addItem(locationData item) {
        items.add(item);
    }

    public List<locationData> getItems() {
        return items;
    }

    public locationData findItemById(int id) {
        for (locationData d : items) {
            if (d.getKey() == id) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }
}

