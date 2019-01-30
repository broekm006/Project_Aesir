/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to handle database interactions such as gathering data and inserting data.
 **
 ***/

package com.uva.aesir.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.uva.aesir.Model.ListName;

public class ListNameDatabase extends SQLiteOpenHelper {
    private static ListNameDatabase instance;
    SQLiteDatabase sqLiteDatabase;


    public ListNameDatabase(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static ListNameDatabase getInstance(Context context) {
        if (instance == null) {
            return instance = new ListNameDatabase(context, "com.uva.aesir", null, 1);
        } else {
            return instance;
        }
    }


    // select everything from the listName table
    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT * FROM listName"), null);
    }


    // select DISTINCT / UNIQUE title(s) from the listName table
    public Cursor selectDistinctNames() {
        return getWritableDatabase().rawQuery(("SELECT DISTINCT title FROM listName"), null);
    }


    // insert data into listName table
    public void insert(ListName insertion) {
        ContentValues value = new ContentValues();
        value.put("title", insertion.getListName());
        getWritableDatabase().insert("listName", null, value);
    }


    // delete data from listName based on id
    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from listName where _id='" + id + "'");
    }


    // create database with all the tables to avoid missing data / tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table presets (_id INTEGER PRIMARY KEY, exercise_name TEXT, title TEXT, numberOfTimes TEXT)");
        sqLiteDatabase.execSQL("create table exercises (_id INTEGER PRIMARY KEY, idex TEXT, title TEXT, description TEXT, category TEXT)");
        sqLiteDatabase.execSQL("create table exerciseImgs (_id INTEGER PRIMARY KEY, idex TEXT, imgUrl TEXT)");
        sqLiteDatabase.execSQL("create table listName (_id INTEGER PRIMARY KEY, title TEXT)");
        sqLiteDatabase.execSQL("create table cardio (_id INTEGER PRIMARY KEY, km INT, speed INT, time INT, activity TEXT)");
        sqLiteDatabase.execSQL("create table weights (_id INTEGER PRIMARY KEY, exercise TEXT, setA TEXT, setB TEXT, setC TEXT, setD TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table presets");
        onCreate(sqLiteDatabase);
    }
}
