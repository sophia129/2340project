package Model;

import android.util.Log;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.LocationStuff.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Handles logic for the location controller
 */
public class locationModel {

    private static final LocationSQLiteDBHandler db = MainActivity.getLocationsDb();

    /**
     * Method that returns an array with all the names of the locations
     * @return the list of locations as strings
     */
    public static String[] returnLocationNames() {
        List<Location> allLocations = db.allLocations();
        int locationsSize = allLocations.size();
        String[] toReturn = new String[locationsSize];
        int index = 0;
        for (Location location : db.allLocations()) {
            toReturn[index] = location.getName();
            ++index;
        }
        return toReturn;
    }

    /**
     * Method that returns an array with all the names of the locations
     * @return the list of locations as Locations
     */
    public static Location[] returnLocations() {
        List<Location> allLocations = db.allLocations();
        int locationsSize = allLocations.size();
        Location[] toReturn = new Location[locationsSize];
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
     *
     * @param locationInput the file data to be interpreted and split for the locations
     */
    public static void readLocationData(InputStream locationInput) {

        String line = null;
        try {
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

                List<Location> allLocations = db.allLocations();
                boolean containsNewLocation = allLocations.contains(newLocation);

                if (!containsNewLocation) {
                    db.addLocation(newLocation);
                }
            }
        } catch (IOException e) {
            Log.wtf("Activity: location", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }
}
