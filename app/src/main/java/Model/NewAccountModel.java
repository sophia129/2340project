package Model;

public class NewAccountModel {

    /**
     * @param password
     * @param confirmPassword
     * @return true is the two parameters match, else false
     */
    public static boolean checkPasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
