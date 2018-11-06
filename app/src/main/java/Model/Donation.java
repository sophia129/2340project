package Model;

/**
 * Encapsulates data for a donation
 */
public class Donation {

    private final String item;
    private final String description;
    private final String timestamp;
    private final String value;
    private final Location location;
    private final String category;
    private final String comments;


    /**
     * Constructor for the donation that encapsulates all of its required attributes
     * @param item the short description or name
     * @param description the full/long description
     * @param timestamp when the donation occurred
     * @param value the worth (in USD) of the object
     * @param location the location where the donation occurred
     * @param category the type the item fits into
     * @param comments holds any extra information
     */
    public Donation(String item, String description, String timestamp, String value,
                    Location location, String category, String comments) {
        this.item = item;
        this.description = description;
        this.timestamp = timestamp;
        this.value = value;
        this.location = location;
        this.category = category;
        this.comments = comments;
    }

    /**
     * Getter method for the donation's short description/name
     * @return the short description/name
     */
    public String getItem() {
        return this.item;
    }

    /**
     * Getter method for the donation's full/long description
     *
     * @return the full/long description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter method for when the donation occurred
     *
     * @return the time when the donation occurred
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     * Getter method for the donation's value in USD
     *
     * @return the value in USD
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Getter method for the location where the donation occurred
     *
     * @return the location where the donation occurred
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Getter method for the donation's designated category
     *
     * @return the designated category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Getter method for the donation's list of miscellaneous comments
     *
     * @return the comments
     */
    public String getComments() { return this.comments; }

    /**
     * Gives a string description that includes all the details of the donation
     */
    public String toString() { return this.item + "\n" + this.description+ "\n" + this.timestamp
            + "\n" + this.value + "\n" + this.location.getName() + "\n" + this.category
            + "\n" + this.comments; }

}
