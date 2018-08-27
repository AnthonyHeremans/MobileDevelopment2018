package be.pxl.com.mybbq.SQLLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class FeedDatabase {

    private FeedDatabase(){}

    public static class FeedEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "eventTable";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EVENTNAME = "eventname";
        public static final String COLUMN_NAME_ADRESS = "adress";
        public static final String COLUMN_NAME_HOUSENUMBER = "houseNumber";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_POSTALCODE = "postalCode";
        public static final String COLUMN_NAME_MAXATTENDEES = "maxAttendees";
        public static final String COLUMN_NAME_ATTENDEES = "attendees";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_FILTERS = "filters";
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    FeedEntry.COLUMN_NAME_EVENTNAME + " TEXT," +
                    FeedEntry.COLUMN_NAME_ADRESS + " TEXT," +
                    FeedEntry.COLUMN_NAME_HOUSENUMBER + " TEXT," +
                    FeedEntry.COLUMN_NAME_CITY + " TEXT," +
                    FeedEntry.COLUMN_NAME_POSTALCODE + " TEXT," +
                    FeedEntry.COLUMN_NAME_MAXATTENDEES + " TEXT," +
                    FeedEntry.COLUMN_NAME_ATTENDEES + " TEXT," +
                    FeedEntry.COLUMN_NAME_DATE + " TEXT," +
                    FeedEntry.COLUMN_NAME_TIME + " TEXT," +
                    FeedEntry.COLUMN_NAME_COMMENT + " TEXT," +
                    FeedEntry.COLUMN_NAME_FILTERS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static class FeedDatabaseDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "BbqDatabase.db";

        public FeedDatabaseDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }
}
