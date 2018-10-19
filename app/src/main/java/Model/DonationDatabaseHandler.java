package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.breadscrumbs.donation_tracker.MainActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Model.Donation;

public class DonationDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "DonationsDB";

    LocationSQLiteDBHandler dbLocations = MainActivity.getLocationsDb();

    private static final String TABLE_1 = "T1";
    private static final String TABLE_2 = "T2";
    private static final String TABLE_3 = "T3";
    private static final String TABLE_4 = "T4";
    private static final String TABLE_5 = "T5";
    private static final String TABLE_6 = "T6";

    private static final String KEY_ITEM = "item";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_VALUE = "value";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_COMMENTS = "comments";

    String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_1 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";
    String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_2 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";
    String CREATE_TABLE_3 = "CREATE TABLE " + TABLE_3 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";
    String CREATE_TABLE_4 = "CREATE TABLE " + TABLE_4 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";
    String CREATE_TABLE_5 = "CREATE TABLE " + TABLE_5 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";
    String CREATE_TABLE_6 = "CREATE TABLE " + TABLE_6 + " (" + KEY_ITEM + " TEXT PRIMARY KEY, " +
            KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, " + KEY_VALUE + " TEXT, " +
            KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, " + KEY_COMMENTS + " TEXT);";


//    private static final String KEY_LOCATION = "location";


    private static final String[] COLUMNS = {KEY_ITEM, KEY_DESCRIPTION, KEY_TIMESTAMP, KEY_VALUE, KEY_LOCATION, KEY_CATEGORY, KEY_COMMENTS};

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
        sqLiteDatabase.execSQL(CREATE_TABLE_1);
        sqLiteDatabase.execSQL(CREATE_TABLE_2);
        sqLiteDatabase.execSQL(CREATE_TABLE_3);
        sqLiteDatabase.execSQL(CREATE_TABLE_4);
        sqLiteDatabase.execSQL(CREATE_TABLE_5);
        sqLiteDatabase.execSQL(CREATE_TABLE_6);
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_5);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_6);
        this.onCreate(sqLiteDatabase);
    }

    /**
     * @return the donation that the user has requested
     */
    public Donation getItem(String locationKey, String item) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + "T" + locationKey + " WHERE item= ?";
        System.out.println("QUERY" +query);

        Cursor cursor = db.rawQuery(query, new String[] {item});

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Donation foundItem = new Donation(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), dbLocations.getLocation(cursor.getString(4)), cursor.getString(5), cursor.getString(6));

        cursor.close();
        return foundItem;
//        return null;
    }

    /**
     * @return list of all donations in the database
     */
    public List<Donation> allItems(String key) {
        List<Donation> items = new LinkedList<>();
        String query = "SELECT * FROM " + "T" + key;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donation item = null;

        if (cursor.moveToFirst()) {
            do {
                item = new Donation(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), dbLocations.getLocation(cursor.getString(4)), cursor.getString(5), cursor.getString(6));
                items.add(item);
            } while (cursor.moveToNext());
        }

        return items;
    }

    /**
     * Adds a donation to the database
     * @param item
     */
    public void addDonation(Donation item, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM, item.getItem());
        values.put(KEY_DESCRIPTION, item.getDescription());
        values.put(KEY_TIMESTAMP, item.getTimestamp());
        values.put(KEY_VALUE, item.getValue());
        values.put(KEY_LOCATION, item.getLocation().getKey());
        values.put(KEY_CATEGORY, item.getCategory());
        values.put(KEY_COMMENTS, item.getComments());

        System.out.println(values);

        db.insert("T" + key, null, values);
        db.close();

    }

}
