package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Model.User;

public class DonationDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "DonationsDB";
    private static final String TABLE_NAME = "Donations";
    private static final String KEY_ITEM = "item";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_VALUE = "value";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CATEGORY = "category";

    private static final String[] COLUMNS = {KEY_ITEM, KEY_DESCRIPTION, KEY_TIMESTAMP, KEY_VALUE, KEY_CATEGORY};

    /**
     * Initialize the Database Handler
     *
     * @param context
     */
    public DonationDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Creates Database Handler
     * @param sqLiteDatabase
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
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    /**
     * Delete a User object from the database using key field -> email
     * @param user
     */
//    public void deleteOne(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, KEY_EMAIL + " = ?", new String[]{String.valueOf(user.getUserEmail())});
//        db.close();
//    }

    /**
     * Retrieves a user from the database
     *
     * @param email
     * @return
     */
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                KEY_EMAIL + " = ?",
                new String[] {KEY_EMAIL},
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), User.UserType.valueOf(cursor.getString(3).toUpperCase()));

        return user;
    }

    /**
     * @return list of all users in the database
     */
    public List<User> allUsers() {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;

        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2),
                        User.UserType.valueOf(cursor.getString(3).toUpperCase()));
                users.add(user);
            } while (cursor.moveToNext());
        }

        return users;
    }

    /**
     * Adds a user to the database
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUserName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_TYPE, user.getUserType().getUserTypeString());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Removes all users from the database using Delete query
     */
//    public void removeAllUsers() {
//        String query = "DELETE FROM " + TABLE_NAME;
//        getWritableDatabase().execSQL(query);
//    }

    /**
     * Updates a user with instance variables from the user passed in
     * @param user
     * @return
     */
//    public int updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, user.getUserName());
//        values.put(KEY_EMAIL, user.getUserEmail());
//        values.put(KEY_PASSWORD, user.getPassword());
//        values.put(KEY_TYPE, user.getUserType().getUserTypeString());
//
//        int i = db.update(TABLE_NAME,
//                values,
//                KEY_EMAIL + " = ?",
//                new String[]{KEY_EMAIL});
//        db.close();
//        return i;
//
//    }




}

