package Model;

public class LogInModel {

    /**
     * Checks the passed in userName and password with the ones on file.
     * If the credentials are valid, true is returned; otherwise, false is returned.
     */
    public static boolean validSignIn(String userName, String password) {
        if (userName.equals("user") && password.equals("pass")) {
            return true;
        } else {
            return false;
        }
    }
}
