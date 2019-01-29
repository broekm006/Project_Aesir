package com.uva.aesir.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.uva.aesir.Model.Weights;

public class WeightsDatabase extends SQLiteOpenHelper {
    private static WeightsDatabase instance;
    SQLiteDatabase sqLiteDatabase;


    public WeightsDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static WeightsDatabase getInstance(Context context) {
        if (instance == null) {
            return instance = new WeightsDatabase(context, "com.uva.aesir", null, 1);
        } else {
            return instance;
        }
    }


    // get all information from the weights table
    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT * FROM weights GROUP BY exercise"), null);
    }


    // get all information from the weights table in descending order
    public Cursor selectResults(String exercise) {
        return getWritableDatabase().rawQuery(("SELECT * FROM weights WHERE exercise = '" + exercise + "' ORDER BY _id DESC"), null);
    }


    // insert new value in the weights table
    public void insert(Weights insertion) {
        ContentValues value = new ContentValues();
        value.put("exercise", insertion.getExercise());
        value.put("setA", insertion.getSetA());
        value.put("setB", insertion.getSetB());
        value.put("setC", insertion.getSetC());
        value.put("setD", insertion.getSetD());

        getWritableDatabase().insert("weights", null, value);
    }


    // create database with all the tables to avoid missing data / tables
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
        sqLiteDatabase.execSQL("drop table weights");
        onCreate(sqLiteDatabase);
    }
}