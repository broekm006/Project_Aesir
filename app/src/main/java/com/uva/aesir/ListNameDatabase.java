package com.uva.aesir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

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

    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT * FROM listName"), null);
    }


    public void insert(ListName insertion) {
        ContentValues value = new ContentValues();
        value.put("title", insertion.getListName());
        getWritableDatabase().insert("listName", null, value);
    }

    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from listName where _id='" + id + "'");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table presets (_id INTEGER PRIMARY KEY, exercise_name TEXT, title TEXT, numberOfTimes TEXT)");
        sqLiteDatabase.execSQL("create table exercises (_id INTEGER PRIMARY KEY, idex TEXT, title TEXT, description TEXT, category TEXT)");
        sqLiteDatabase.execSQL("create table exerciseImgs (_id INTEGER PRIMARY KEY, idex TEXT, imgUrl TEXT)");
        sqLiteDatabase.execSQL("create table listName (_id INTEGER PRIMARY KEY, title TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table presets");
        onCreate(sqLiteDatabase);
    }
}
