package Model;

import android.util.Log;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.LocationStuff.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class locationModel {

    static LocationSQLiteDBHandler db = MainActivity.getLocationsDb();

    /**
     * Method that returns an array with all the names of the locations
     */
    public static String[] returnLocationNames() {
        String[] toReturn = new String[db.allLocations().size()];
        int index = 0;
        for (Location location : db.allLocations()) {
            toReturn[index] = location.getName();
            ++index;
        }
        return toReturn;
    }

    /**
     * Method that returns an array with all the names of the locations
     */
    public static Location[] returnLocations() {
        Location[] toReturn = new Location[db.allLocations().size()];
        int index = 0;
        for (Location location : db.allLocations()) {
            toReturn[index] = location;
            ++index;
        }
        return toReturn;
    }

    /**
     * Reads in the data from the CSV file by parsing it by its commas;
     * takes these separated components to create a location that is added to the
     * local database of locations
     */
    public static void readLocationData(InputStream locationInput) {

        String line = null;
        try {
            //InputStream locationInput = getResources().openRawResource(R.raw.locationdata);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(locationInput, StandardCharsets.UTF_8)
            );

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d(location.TAG, line);
                //split by comma
                Log.d("Line", line);
                String[] tokens = line.split(",");

                Location newLocation = new Location(tokens[Location.SaveEquivalencies.key.value],
                        tokens[Location.SaveEquivalencies.name.value],
                        tokens[Location.SaveEquivalencies.latitude.value],
                        tokens[Location.SaveEquivalencies.longitude.value],
                        tokens[Location.SaveEquivalencies.streetAddress.value],
                        tokens[Location.SaveEquivalencies.city.value],
                        tokens[Location.SaveEquivalencies.state.value],
                        tokens[Location.SaveEquivalencies.zip.value],
                        tokens[Location.SaveEquivalencies.type.value],
                        tokens[Location.SaveEquivalencies.phone.value],
                        tokens[Location.SaveEquivalencies.website.value]);


                if (!db.allLocations().contains(newLocation)) {
                    db.addLocation(newLocation);
                }
            }
        } catch (IOException e) {
            Log.wtf("Activity: location", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }
}
