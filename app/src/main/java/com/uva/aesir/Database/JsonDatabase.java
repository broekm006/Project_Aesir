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

import com.uva.aesir.Model.Exercise;
import com.uva.aesir.Model.ExerciseImg;

public class JsonDatabase extends SQLiteOpenHelper {
    private static JsonDatabase instance;
    SQLiteDatabase sqLiteDatabase;


    public JsonDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static JsonDatabase getInstance(Context context) {
        if (instance == null) {
            return instance = new JsonDatabase(context, "com.uva.aesir", null, 1);
        } else {
            return instance;
        }
    }


    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT * FROM exercises LEFT JOIN exerciseImgs ON " +
                "exercises.idex = exerciseImgs.idex"), null);
    }


    public Cursor selectName() {
        return getWritableDatabase().rawQuery(("SELECT title FROM exercises"), null);
    }


    // insert data into the exercises table
    public void insert(Exercise insertion) {
        ContentValues value = new ContentValues();
        value.put("idex", insertion.getIdex());
        value.put("title", insertion.getName());
        value.put("description", insertion.getDescription());
        value.put("category", insertion.getCategorie());

        Cursor cursor = null;
        cursor = getWritableDatabase().rawQuery(("SELECT idex FROM exercises WHERE idex =" + insertion.getIdex()), null);

        // check if actual data exists
        if (cursor.getCount() > 0) {
        } else {
            getWritableDatabase().insert("exercises", null, value);
        }

        cursor.close();
    }


    // insert image url(s) into exerciseImgs table
    public void insertImg(ExerciseImg insertion) {
        ContentValues value = new ContentValues();
        value.put("idex", insertion.getIdex());
        value.put("imgUrl", insertion.getImgUrl());

        Cursor cursor = null;
        cursor = getWritableDatabase().rawQuery("SELECT * FROM exerciseImgs WHERE idex='" + insertion.getIdex() + "'", null);

        // check if actual data excists
        if (cursor.getCount() > 0) {
        } else {
            getWritableDatabase().insert("exerciseImgs", null, value);
        }

        cursor.close();
    }


    // if there are exercises without name delete them from the exercises table
    public void deleteEmptyExercises() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from exercises where title = ''");
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
        sqLiteDatabase.execSQL("drop table exercises");
        onCreate(sqLiteDatabase);
    }
}
