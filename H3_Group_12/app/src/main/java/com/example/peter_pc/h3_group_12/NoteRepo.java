package com.example.peter_pc.h3_group_12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class NoteRepo {
    private DBHelper dbHelper;

    public NoteRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Note note) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.KEY_age, note.age);
        values.put(Note.KEY_email,note.email);
        values.put(Note.KEY_name, note.name);

        // Inserting Row
        long note_Id = db.insert(Note.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) note_Id;
    }

    public void delete(int note_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Note.TABLE, Note.KEY_ID + "= ?", new String[] { String.valueOf(note_Id) });
        db.close(); // Closing database connection
    }

    public void update(Note note) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Note.KEY_age, note.age);
        values.put(Note.KEY_email,note.email);
        values.put(Note.KEY_name, note.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Note.TABLE, values, Note.KEY_ID + "= ?", new String[] { String.valueOf(note.note_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getNoteList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Note.KEY_ID + "," +
                Note.KEY_name + "," +
                Note.KEY_email + "," +
                Note.KEY_age +
                " FROM " + Note.TABLE;


        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> note = new HashMap<String, String>();
                note.put("id", cursor.getString(cursor.getColumnIndex(Note.KEY_ID)));
                note.put("name", cursor.getString(cursor.getColumnIndex(Note.KEY_name)));
                noteList.add(note);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return noteList;

    }

    public Note getNoteById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Note.KEY_ID + "," +
                Note.KEY_name + "," +
                Note.KEY_email + "," +
                Note.KEY_age +
                " FROM " + Note.TABLE
                + " WHERE " +
                Note.KEY_ID + "=?";

        int iCount =0;
        Note note = new Note();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                note.note_ID =cursor.getInt(cursor.getColumnIndex(Note.KEY_ID));
                note.name =cursor.getString(cursor.getColumnIndex(Note.KEY_name));
                note.email  =cursor.getString(cursor.getColumnIndex(Note.KEY_email));
                note.age =cursor.getInt(cursor.getColumnIndex(Note.KEY_age));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return note;
    }

}
