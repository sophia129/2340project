package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class LocationSQLiteDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "LocationsDB";
    private static final String TABLE_NAME = "Locations";
    private static final String KEY_KEY = "numKey";
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_STREETADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_ZIP = "zip";
    private static final String KEY_TYPE = "type";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_WEBSITE = "website";

    private static final String[] COLUMNS = {KEY_KEY, KEY_NAME, KEY_LATITUDE, KEY_LONGITUDE,
            KEY_STREETADDRESS, KEY_CITY, KEY_STATE, KEY_ZIP, KEY_TYPE, KEY_PHONE, KEY_WEBSITE};

    /**
     * Initialize the Database Handler
     *
     * @param context
     */
    public LocationSQLiteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Creates Database Handler
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_KEY + " TEXT PRIMARY KEY, " +
                KEY_NAME + " TEXT, " + KEY_LATITUDE + " TEXT, " + KEY_LONGITUDE + " TEXT, " + KEY_STREETADDRESS + " TEXT, " + KEY_CITY
                + " TEXT, " + KEY_STATE  + " TEXT, " + KEY_ZIP  + " TEXT, " + KEY_TYPE  + " TEXT, " + KEY_PHONE  + " TEXT, " + KEY_WEBSITE + " TEXT);";
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
     * Retrieves a location from the database from its location
     *
     * @param key
     * @return
     */
    public Location getLocation(String key) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE numKey="+key+"";
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Location location = new Location(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                cursor.getString(10));

        cursor.close();
        return location;
    }

    /**
     * Adds a location to the database
     * @param location
     */
    public void addLocation(Location location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_KEY, location.getKey());
        values.put(KEY_NAME, location.getName());
        values.put(KEY_LATITUDE, location.getLatitude());
        values.put(KEY_LONGITUDE, location.getLongitude());
        values.put(KEY_STREETADDRESS, location.getStreetAddress());
        values.put(KEY_CITY, location.getCity());
        values.put(KEY_STATE, location.getState());
        values.put(KEY_ZIP, location.getZip());
        values.put(KEY_TYPE, location.getType());
        values.put(KEY_PHONE, location.getPhone());
        values.put(KEY_WEBSITE, location.getWebsite());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    /**
     * @return list of all locations in the database
     */
    public List<Location> allLocations() {
        List<Location> locations = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Location location = null;

        if (cursor.moveToFirst()) {
            do {
                location = new Location(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                        cursor.getString(10));
                locations.add(location);
            } while (cursor.moveToNext());
        }

        return locations;
    }

}
