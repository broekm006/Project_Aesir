/*
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to create a new database + tables and have all the major db methods.
 **/

package com.uva.aesir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CardioDatabase extends SQLiteOpenHelper {
    private static CardioDatabase instance;
    SQLiteDatabase sqLiteDatabase;

    public CardioDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static CardioDatabase getInstance(Context context) {
        if (instance == null) {
            return instance = new CardioDatabase(context, "com.uva.aesir", null, 1);
        } else {
            return instance;
        }
    }

    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT * FROM cardio ORDER BY _id DESC"), null);
    }

    public void insert(Cardio insertion) {
        ContentValues value = new ContentValues();
        value.put("km", insertion.getKm());
        value.put("speed", insertion.getSpeed());
        value.put("time", insertion.getTime());
        value.put("activity", insertion.getActivity());

        getWritableDatabase().insert("cardio", null, value);
    }

    public void deleteCardio(long id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from cardio where _id ='" + id + "'");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table exercises (_id INTEGER PRIMARY KEY, idex TEXT, title TEXT, description TEXT, category TEXT)");
        sqLiteDatabase.execSQL("create table presets (_id INTEGER PRIMARY KEY, exercise_name TEXT, title TEXT)");
        sqLiteDatabase.execSQL("create table exerciseImgs (_id INTEGER PRIMARY KEY, idex TEXT, imgUrl TEXT)");
        sqLiteDatabase.execSQL("create table listName (_id INTEGER PRIMARY KEY, title TEXT)");
        sqLiteDatabase.execSQL("create table cardio (_id INTEGER PRIMARY KEY, km INT, speed INT, time INT, activity TEXT)");
        sqLiteDatabase.execSQL("create table weights (_id INTEGER PRIMARY KEY, exercise TEXT, setA TEXT, setB TEXT, setC TEXT, setD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table cardio");
        onCreate(sqLiteDatabase);
    }
}