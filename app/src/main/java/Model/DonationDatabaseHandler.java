package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.breadscrumbs.donation_tracker.MainActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * SQLiteDatabase that handles adding and retrieving donations
 */
public class DonationDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "DonationsDB";

    private final LocationSQLiteDBHandler dbLocations = MainActivity.getLocationsDb();

    private static final String TABLE_1 = "T1"; //database for location 1
    private static final String TABLE_2 = "T2"; //database for location 2
    private static final String TABLE_3 = "T3"; //database for location 3
    private static final String TABLE_4 = "T4"; //database for location 4
    private static final String TABLE_5 = "T5"; //database for location 5
    private static final String TABLE_6 = "T6"; //database for location 6
    private static final String TABLE_7 = "T7"; //database compilation of all locations

    private static final String KEY_ITEM = "item";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_VALUE = "value";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_COMMENTS = "comments";


    //private static final String[] COLUMNS = {KEY_ITEM, KEY_DESCRIPTION, KEY_TIMESTAMP, KEY_VALUE,
    //        KEY_LOCATION, KEY_CATEGORY, KEY_COMMENTS};

    /**
     * Initialize the Database Handler
     *
     * @param context The current interface to set up the database
     */
    public DonationDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates Database Handler
     * @param sqLiteDatabase The empty database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_1 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_1);
        String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_2 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_2);
        String CREATE_TABLE_3 = "CREATE TABLE " + TABLE_3 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_3);
        String CREATE_TABLE_4 = "CREATE TABLE " + TABLE_4 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_4);
        String CREATE_TABLE_5 = "CREATE TABLE " + TABLE_5 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_5);
        String CREATE_TABLE_6 = "CREATE TABLE " + TABLE_6 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_6);
        String CREATE_TABLE_7 = "CREATE TABLE " + TABLE_7 + " (" + KEY_ITEM
                + " TEXT PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_TIMESTAMP + " TEXT, "
                + KEY_VALUE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_CATEGORY + " TEXT, "
                + KEY_COMMENTS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_7);
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_5);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_6);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_7);
        this.onCreate(sqLiteDatabase);
    }

    /**
     * Returns a donation based off the specified location and the name
     *
     * @param locationKey the identifier for the current location
     * @param item the name of the item to be retrieved
     * @return the donation that the user has requested
     */
    public Donation getItem(String locationKey, String item) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + "T" + locationKey + " WHERE item= ?";
        //System.out.println("QUERY" +query);

        Cursor cursor = db.rawQuery(query, new String[] {item});

        if (cursor != null) {
            cursor.moveToFirst();
        }

        assert cursor != null;
        Donation foundItem = new Donation(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                dbLocations.getLocation(cursor.getString(4)),
                cursor.getString(5), cursor.getString(6));

        cursor.close();
        return foundItem;
//        return null;
    }

    /**
     * finds a particular item from all locations
     * @param Item name of the item that's being searched for
     * @param location The chosen location to search (null if for all locations)
     * @return the particular donation item
     */
    public List<Donation> getPotentialItem(String Item, Location location) {

        boolean useLocation = (location != null);
        List<Donation> items = new LinkedList<>();
        Log.d("Item", Item);
        String query = "SELECT * FROM " + TABLE_7;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donation potentialItem;

        if (cursor.moveToFirst()) {
            do {
                potentialItem = new Donation(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        dbLocations.getLocation(cursor.getString(4)),
                        cursor.getString(5), cursor.getString(6));
                String shortDescription = potentialItem.getItem();
                shortDescription = shortDescription.toLowerCase();
                final String lowercaseItem = Item.toLowerCase();
                boolean doesContain = shortDescription.contains(lowercaseItem);
                if (doesContain) {
                    //Log.d("Item Location", potentialItem.getLocation().getName());
                    //final Location potentialItemLocation = potentialItem.getLocation();
                    final String key = potentialItem.getLocationKey();
                    if (!useLocation) {
                        items.add(potentialItem);
                    } else {
                        boolean equivalent = key.equals(location.getKey());
                        if (equivalent) {
                            items.add(potentialItem);
                        }
                    }
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    /**
     * Queries into main database and finds all donations from all locations
     *
     * @return list of all items regardless of locations
     */
    public List<Donation> getAllDonationItems() {

        List<Donation> items = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_7;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donation item;

        if (cursor.moveToFirst()) {
            do {
                item = new Donation(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        dbLocations.getLocation(cursor.getString(4)),
                        cursor.getString(5), cursor.getString(6));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    /**
     *
     * @return gets every single donation item from all locations
     */
    public List<String> getAllDonationItemNames() {
        List<String> result = new ArrayList<>();
        String query = "SELECT item FROM " + TABLE_7;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do{
                result.add(cursor.getString((cursor.getColumnIndex("item"))));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return result;
    }

    /**
     * Returns a list of all items for a particular location
     *
     * @param key the identifier for the location
     * @return list of all donations in the database for a particular location
     */
    public List<Donation> allItems(String key) {
        List<Donation> items = new LinkedList<>();
        String query = "SELECT * FROM " + "T" + key;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donation item;

        if (cursor.moveToFirst()) {
            do {
                item = new Donation(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        dbLocations.getLocation(cursor.getString(4)),
                        cursor.getString(5), cursor.getString(6));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    /**
     * Query for finding donations from particular categories.
     * @param Category the category being searched for
     * @param location the locations the donation object currently is found at
     * @return a list of donations that meet the category being searched for from all locations
     */

    public List<Donation> getCategoryItems(String Category, Location location) {

        boolean useLocation = (location != null);

        List<Donation> items = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_7 + " WHERE category= ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] {Category});
        Donation item;
        if (cursor.moveToFirst()) {
            do {
                item = new Donation(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        dbLocations.getLocation(cursor.getString(4)),
                        cursor.getString(5), cursor.getString(6));
                //final Location itemLocation = item.getLocation();
                //final String key = itemLocation.getKey();
                final String key = item.getLocationKey();
                if (!useLocation) {
                    items.add(item);
                } else {
                    boolean equivalent = key.equals(location.getKey());
                    if (equivalent) {
                        items.add(item);
                    }
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    /**
     * Adds a donation to the database
     * @param item the item to be added
     * @param key the identifier for the table number to use
     */
    public void addDonation(Donation item, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //final Location itemLocation = item.getLocation();
        //final String locationKey = itemLocation.getKey();

        final String locationKey = item.getLocationKey();

        values.put(KEY_ITEM, item.getItem());
        values.put(KEY_DESCRIPTION, item.getDescription());
        values.put(KEY_TIMESTAMP, item.getTimestamp());
        values.put(KEY_VALUE, item.getValue());
        values.put(KEY_LOCATION, locationKey);
        values.put(KEY_CATEGORY, item.getCategory());
        values.put(KEY_COMMENTS, item.getComments());

        db.insert("T" + key, null, values);
        db.close();
    }

    /**
     *
     * @param item the donation item being added
     */
    public void addDonation(Donation item) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //final Location itemLocation = item.getLocation();
        //final String locationKey = itemLocation.getKey();

        final String locationKey = item.getLocationKey();

        values.put(KEY_ITEM, item.getItem());
        values.put(KEY_DESCRIPTION, item.getDescription());
        values.put(KEY_TIMESTAMP, item.getTimestamp());
        values.put(KEY_VALUE, item.getValue());
        values.put(KEY_LOCATION, locationKey);
        values.put(KEY_CATEGORY, item.getCategory());
        values.put(KEY_COMMENTS, item.getComments());

        db.insert("T7", null, values);
        db.close();
    }
}

