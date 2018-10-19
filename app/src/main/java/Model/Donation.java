package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Donation {

    private String item;
    private String description;
    private String timestamp;
    private String value;
    private String location;
    private String category;

    public Donation(String item, String description, String timestamp, String value, String location, String category) {
        this.item = item;
        this.description = description;
        this.timestamp = timestamp;
        this.value = value;
        this.location = location;
        this.category = category;
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
    public String getLocation() {
        return this.location;
    }
    public String getCategory() {
        return this.category;
    }

    public String toString() { return this.item + "\n" + this.description+ "\n" + this.timestamp + "\n" + this.value + "\n" + this.location + "\n" + this.category + "\n"; }

}
