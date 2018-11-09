package Model;

/**
 * Encapsulates all the information about the location
 */
public class Location {

    /**
     * Enum for a corresponding name for each index; avoids magic numbers
     */
    public enum SaveEquivalencies {
        key(0),
        name(1),
        latitude(2),
        longitude(3),
        streetAddress(4),
        city(5),
        state(6),
        zip(7),
        type(8),
        phone(9),
        website(10);

        public final int value;

        /**
         * Enum constructor that assigns a corresponding value to each enum value
         * @param value the corresponding value to be associated with each enum value
         */
        SaveEquivalencies(int value) {
            this.value = value;
        }

    }

    /**
     * Getter method for the location's key
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Getter method for the location's name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the location's latitude
     *
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Getter method for the location's longitude
     *
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Getter method for the location's street address
     *
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Getter method for the location's city
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter method for the location's state
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method for the location's ZIP code
     *
     * @return the ZIP code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Getter method for the location's type
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method for the location's phone number
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Getter method for the location's web address
     *
     * @return the web address
     */
    public String getWebsite() {
        return website;
    }

    private final String key;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String zip;
    private final String type;
    private final String phone;
    private final String website;

    /**
     *
     * Constructor for the location that encapsulates all of its required attributes
     * @param key each location's unique key
     * @param name each location's name
     * @param latitude each location's latitude
     * @param longitude each location's longitude
     * @param streetAddress each location's street address
     * @param city each location's city
     * @param state each location's state
     * @param zip each location's ZIP code
     * @param type each location's type
     * @param phone each location's phone number
     * @param website each location's web address
     */
    public Location(String key, String name, String latitude, String longitude,
                    String streetAddress, String city, String state, String zip, String type,
                    String phone, String website) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    @Override
    public String toString() {
        String forTV = "";

        forTV += "Key\n" + this.getKey() + "\n\n";
        forTV += "Name\n" + this.getName() + "\n\n";
        forTV += "Latitude\n" + this.getLatitude() + "\n\n";
        forTV += "Longitude\n" + this.getLongitude() + "\n\n";
        forTV += "Street Address\n" + this.getStreetAddress() + "\n\n";
        forTV += "City\n" + this.getCity() + "\n\n";
        forTV += "State\n" + this.getState() + "\n\n";
        forTV += "ZIP\n" + this.getZip() + "\n\n";
        forTV += "Type\n" + this.getType() + "\n\n";
        forTV += "Phone\n" + this.getPhone() + "\n\n";
        forTV += "Website\n" + this.getWebsite() + "\n\n";

        return forTV;
    }
}
