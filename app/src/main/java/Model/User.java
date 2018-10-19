package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public enum UserType {
        USER("USER"), LOCATIONEMPLOYEE("LOCATION EMPLOYEE"), ADMIN("ADMIN"), MANAGER("MANAGER");

        private String userTypeString;

        UserType(String userTypeString) {
            this.userTypeString = userTypeString;
        }

        public String getUserTypeString() {
            return this.userTypeString;
        }

    }

    private String userName;
    private String userEmail;
    private String password;
    private UserType userType;

    public User(String userName, String userEmail, String password, UserType userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userType = userType;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

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

    public String toString() {
        return userName + " : " + userEmail + ", " + userType.name();
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

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
     * Returns the user type string with its spaces removed; used for converting to enum's state
     */
    public static String removeSpacesFromUserTypeString(String userTypeString) {
        return userTypeString.replaceAll(" ", "");
    }



}
