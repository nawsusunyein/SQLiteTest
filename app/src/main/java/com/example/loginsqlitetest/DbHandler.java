package com.example.loginsqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DESG = "designation";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_DESG + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }

    //Add user details information
    void insertUserDetailsInformation(String name,String location,String designation){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME,name);
        cValues.put(KEY_LOC,location);
        cValues.put(KEY_DESG,designation);

        long newRowId = db.insert(TABLE_Users,null,cValues);
        db.close();
    }

    //Get All users

    public ArrayList<HashMap<String,String>> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> userList = new ArrayList<>();
        String query = "SELECT name,location,designation FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            userList.add(user);
        }
        return userList;
    }
}