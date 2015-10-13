package com.example.victor.teamproject.DatabaseUtility;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Teardrop Ducky on 12/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String tableName = "likedartists";



    public DatabaseHelper(Context context, String DatabaseName, int version) {
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + tableName + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "artistname TEXT, "
                + "date" + "integer)";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);

        onCreate(db);

    }
}
