package Model;

/**
 * Handles the logic for the NewAccount controller
 */
public class NewAccountModel {

    /**
     * Confirms whether the password is the same as the confirmed password
     *
     * @param password The original password
     * @param confirmPassword The password that should match the original password
     * @return true is the two parameters match, else false
     */
    public static boolean checkPasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
