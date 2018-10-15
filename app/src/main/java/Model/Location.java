package Model;

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

        public int value;

        SaveEquivalencies(int value) {
            this.value = value;
        }

    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    private String key;
    private String name;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website = "";

    /**
     * Constructor for the location with all of its attributes
     */
    public Location(String key, String name, String latitude, String longitude, String streetAddress,
                    String city, String state, String zip, String type, String phone, String website) {
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
}
