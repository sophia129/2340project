package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * SQLiteDatabase that handles adding and retrieving users
 */
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "UsersDB";
    private static final String TABLE_NAME = "Users";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TYPE = "usertype";

    private static final String[] COLUMNS = {KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_TYPE};

    /**
     * Initialize the Database Handler
     *
     * @param context The current interface to set up the database
     */
    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Creates Database Handler
     * @param sqLiteDatabase The empty database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_NAME + " TEXT, " +
                KEY_EMAIL + " TEXT PRIMARY KEY, " + KEY_PASSWORD + " TEXT, " + KEY_TYPE + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    /**
     * Update database management system after changing versions
     *
     * @param sqLiteDatabase the existing SQLiteDatabase to be updated
     * @param oldVersion The existing version of the database
     * @param newVersion The new/updated version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    /*
    public void deleteOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_EMAIL + " = ?",
                new String[]{String.valueOf(user.getUserEmail())});
        db.close();
    }
    */

    /**
     * Retrieves a user from the database
     *
     * @param email the email that is used to identify the user
     * @return the user (as identified by the email)
     */
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                KEY_EMAIL + " = ?",
                new String[] {email},
                null,
                null,
                null
                );
        if (cursor != null) {
            cursor.moveToFirst();
        }


        String userType = (cursor != null) ? cursor.getString(3) : null;
        assert userType != null;
        userType = userType.toUpperCase();

        String userEmail = cursor.getString(0);
        String userName = cursor.getString(1);
        String password = cursor.getString(2);

        cursor.close();

        return new User(userEmail, userName,
                password,
                User.UserType.valueOf(userType));
    }

    /**
     *
     * @param email is the email of the user to fetch
     * @return the UserType of the email of the user passed in
     */
    public User.UserType getUserType(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                KEY_EMAIL + " = ?",
                new String[] {email},
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        String userType = (cursor != null) ? cursor.getString(3) : null;
        assert userType != null;
        userType = userType.toUpperCase();

        cursor.close();

        return User.UserType.valueOf(userType);
    }

    /**
     * Pulls all of the users out of the database and returns it
     *
     * @return list of all users in the database
     */
    public List<User> allUsers() {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user;

        if (cursor.moveToFirst()) {
            do {
                String userType = cursor.getString(3);
                userType = userType.toUpperCase();

                user = new User(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2),
                        User.UserType.valueOf(userType));
               users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return users;
    }

    /**
     * Adds a user to the database
     * @param user the user to add to the database
     */
    public void addUser(User user) {

        final User.UserType userType = user.getUserType();
        final String userTypeName = userType.name();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUserName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_TYPE, userTypeName);
        //values.put(KEY_TYPE, user.getUserType().getUserTypeString());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /*

    public void removeAllUsers() {
        String query = "DELETE FROM " + TABLE_NAME;
        getWritableDatabase().execSQL(query);
    }
    */


    /*
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUserName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_TYPE, user.getUserType().name());

        int i = db.update(TABLE_NAME,
                values,
                KEY_EMAIL + " = ?",
                new String[]{KEY_EMAIL});
        db.close();
        return i;

    }
    */

}
