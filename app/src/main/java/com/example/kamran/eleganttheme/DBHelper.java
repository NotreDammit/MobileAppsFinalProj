package com.example.kamran.eleganttheme;

/**
 * Created by samcholo on 4/10/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "books.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_BOOK = "Book";
    public static String COL_NAME = "book_name";
    public static String COL_ID = "_id";
    public static String TABLE_IMAGES = "Book_Images";
    public static String COL_IMAGE = "image_name";
    public static String COL_IMAGE_ID = "_id";
    public static String COL_BOOK_ID = "book_id";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BOOK + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " ( " + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_BOOK_ID + " INTEGER, " +  COL_IMAGE + " TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + TABLE_BOOK );
        db.execSQL("DROP TABLE if exists " + TABLE_IMAGES );
        onCreate(db);
    }

    public void insertData(String tblname, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblname, null, contentValues );

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(String tblname, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblname, clause, new String[]{Integer.toString(_id)});
        db.close();
    }


    public Cursor getAllEntries(String tblname, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblname, String[] columns, String where, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, where, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblname, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

}
