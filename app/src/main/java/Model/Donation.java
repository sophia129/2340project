package Model;

public class Donation {

    private String item;
    private String description;
    private String timestamp;
    private String value;
    private Location location;
    private String category;
    private String comments;


    public Donation() {
    }

    public Donation(String item, String description, String timestamp, String value, Location location, String category, String comments) {
        this.item = item;
        this.description = description;
        this.timestamp = timestamp;
        this.value = value;
        this.location = location;
        this.category = category;
        this.comments = comments;
    }

    public String getItem() {
        return this.item;
    }
    public String getDescription() {
        return this.description;
    }
    public String getTimestamp() {
        return this.timestamp;
    }
    public String getValue() {
        return this.value;
    }
    public Location getLocation() {
        return this.location;
    }
    public String getCategory() {
        return this.category;
    }
    public String getComments() { return this.comments; }

    public void setItem(String item) {
        this.item = item;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String toString() { return this.item + "\n" + this.description+ "\n" + this.timestamp + "\n" + this.value + "\n" + this.location.getName() + "\n" + this.category + "\n" + this.comments; }

}
