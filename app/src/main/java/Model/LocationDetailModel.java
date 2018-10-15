package Model;

public class LocationDetailModel {

    /**
     * Returns what should be printed in the Location Detail Text View
     */
    public static String returnContents(Location currentLocation)
    {
        String forTV = "";

        forTV += "Key\n" + currentLocation.getKey() + "\n\n";
        forTV += "Name\n" + currentLocation.getName() + "\n\n";
        forTV += "Latitude\n" + currentLocation.getLatitude() + "\n\n";
        forTV += "Longitude\n" + currentLocation.getLongitude() + "\n\n";
        forTV += "Street Address\n" + currentLocation.getStreetAddress() + "\n\n";
        forTV += "City\n" + currentLocation.getCity() + "\n\n";
        forTV += "State\n" + currentLocation.getState() + "\n\n";
        forTV += "ZIP\n" + currentLocation.getZip() + "\n\n";
        forTV += "Type\n" + currentLocation.getType() + "\n\n";
        forTV += "Phone\n" + currentLocation.getPhone() + "\n\n";
        forTV += "Website\n" + currentLocation.getWebsite() + "\n\n";

        return forTV;
    }
}
