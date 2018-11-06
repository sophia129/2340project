package Model;

import com.example.breadscrumbs.donation_tracker.MainActivity;

/**
 * Handles logic for the LogIn controller
 */
public class LogInModel {

    private static final SQLiteDatabaseHandler db = MainActivity.getDb();

    /**
     * Checks the passed in email and password with the ones on file.
     *
     * @param email The email being confirmed for the sign-in
     * @param password The password that should correspond with the given email address
     * @return the validity of the sign in (true if valid, false if not valid)
     */
    public static boolean validSignIn(String email, String password) {
        for (User user : db.allUsers()) {
            final String userPassword = user.getPassword();
            final String userEmail = user.getUserEmail();
            final boolean equalPasswords = userPassword.equals(password);
            final boolean equalEmails = userEmail.equals(email);
            if (equalPasswords && equalEmails)
            {
                return true;
            }
        }
        return false;
    }
}
