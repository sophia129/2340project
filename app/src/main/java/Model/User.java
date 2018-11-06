package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Encapsulates all the attributes about a particular user
 */
public class User implements Parcelable {

    public enum UserType {
        USER("USER"), LOCATION_EMPLOYEE("LOCATION EMPLOYEE"), ADMIN("ADMIN"), MANAGER("MANAGER");

        private final String userTypeString;

        UserType(String userTypeString) {
            this.userTypeString = userTypeString;
        }

        /**
         * Getter method that returns the user's type
         *
         * @return the user's type
         */
        public String getUserTypeString() {
            return this.userTypeString;
        }

    }

    private final String userName;
    private final String userEmail;
    private final String password;
    private final UserType userType;

    /**
     * The constructor that contains all the required attributes for adding a user
     *
     * @param userName the user's name or username
     * @param userEmail the user's unique email address (used for identification purposes)
     * @param password the user's password of at least 8 characters
     * @param userType the selected type of user for different levels of permissions
     */
    public User(String userName, String userEmail, String password, UserType userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Getter method that returns the user's username
     *
     * @return the user's username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Getter method that returns the user's email
     *
     * @return the user's email
     */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
     * Getter method that returns the user's password
     *
     * @return the user's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter method that returns the user's type
     *
     * @return the user's type
     */
    public UserType getUserType() {
        return this.userType;
    }

    /*
    public static int findUserTypePosition(UserType userType) {
        int i = 0;
        while (i < UserType.values().length) {
            if (userType == UserType.values()[i]) {
                return i;
            }
            i++;
        }
        return 0;
    }
    */

    private User(Parcel in) {
        this.userName = in.readString();
        this.userEmail = in.readString();
        this.password = in.readString();
        this.userType = (UserType) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(this.userName);
        destination.writeString(this.userEmail);
        destination.writeString(this.password);
        destination.writeSerializable(userType);
    }

    /**
     * A string representation of the user with all its important qualities
     *
     * @return a string representation of the user
     */
    public String toString() {
        return userName + " : " + userEmail + ", " + userType.name();
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof User)) {
            return false;
        }

        User that = (User) other;

        return (that.userEmail.equalsIgnoreCase(this.userEmail));
    }

    /**
     * Returns the user type string with spaces as underscores; used for converting to enum's state
     *
     * @param userTypeString The original string that contains spaces
     * @return the modified string that contains no spaces
     */
    public static String removeSpacesFromUserTypeString(String userTypeString) {
        return userTypeString.replaceAll(" ", "_");
    }
}
