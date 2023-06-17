package com.coderbd.bmi;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bmi";
    private static final String TABLE_BMI_DATA = "bmi_data";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String STATUS = "status";
    private static final String SCORE = "score";
    private static final String CREATE_DATE = "create_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BMI_DATA_TABLE = "CREATE TABLE " + TABLE_BMI_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + STATUS + " TEXT," +  SCORE + " TEXT,"+  CREATE_DATE + " TEXT" + ")";
        db.execSQL(CREATE_BMI_DATA_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BMI_DATA);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addContact(BmiData bmiData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bmiData.get_name());
        values.put(STATUS, bmiData.get_status());
        values.put(SCORE, bmiData.get_score());
        values.put(CREATE_DATE, String.valueOf(LocalDate.now()));
        // Inserting Row
        db.insert(TABLE_BMI_DATA, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact


    // code to get all contacts in a list view
    public List<BmiData> getAllBmiData() {
        List<BmiData> contactList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BMI_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BmiData contact = new BmiData(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                           // Adding Data to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Deleting single Bmidata
    public void deleteData(BmiData bmiData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BMI_DATA, KEY_ID + " = ?",
                new String[] { String.valueOf(bmiData.get_id()) });
        db.close();
    }

    // Getting Data Count
    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BMI_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}