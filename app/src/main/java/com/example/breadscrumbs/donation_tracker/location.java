package com.example.breadscrumbs.donation_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class location extends AppCompatActivity {
    public static String TAG = "MY_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ListView locationList = (ListView)findViewById(R.id.LocationView);


        readLocationData();

        setContentView(R.layout.activity_location);
    }

    private List<locationData> locationNames = new ArrayList<>();
    /*private void readLocationData() {
        //locationData locationData;
        SimpleModel model = SimpleModel.INSTANCE;
        try {
            InputStream locationInput = getResources().openRawResource(R.raw.LocationData);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(locationInput, StandardCharsets.UTF_8)
            );

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d(location.TAG, line);
                //split by comma
                String[] tokens = line.split(",");
                int key = Integer.parseInt(tokens[0]);
                model.addItem(new locationData(tokens[1], key));
            }
            reader.close();

        } catch (IOException e) {
            Log.e(location.TAG, "error reading assets", e);
        }
    }*/

    private void readLocationData() {
        //locationData locationData;
        String line= null;
        try {
            InputStream locationInput = getResources().openRawResource(R.raw.LocationData);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(locationInput, StandardCharsets.UTF_8)
            );


            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d(location.TAG, line);
                //split by comma
                String[] tokens = line.split(",");

                //read data
                locationData data = new locationData();
                data.setName(tokens[1]);
                //data.setKey(Integer.parseInt(tokens[0]));

                locationNames.add(data);
                Log.d("Activity: location", "Just created", data);
            }
        } catch (IOException e) {
            Log.wtf("Activity: location", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

}
