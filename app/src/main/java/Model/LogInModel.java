package Model;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.SQLiteDatabaseHandler;

public class LogInModel {

    static SQLiteDatabaseHandler db = MainActivity.getDb();

    /**
     * Checks the passed in email and password with the ones on file.
     * If the credentials are valid, true is returned; otherwise, false is returned.
     */
    public static boolean validSignIn(String email, String password) {
        for (User user : db.allUsers()) {
            System.out.println(user.getUserName());
            if (user.getPassword().equals(password) && user.getUserEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
