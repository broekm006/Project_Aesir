package com.uva.aesir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class WeightsDatabase extends SQLiteOpenHelper {
    private static WeightsDatabase instance;
    SQLiteDatabase sqLiteDatabase;

    public WeightsDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static WeightsDatabase getInstance(Context context){
        if (instance == null){
            return instance = new WeightsDatabase(context, "com.uva.aesir", null, 1);
        }

        else{
            return instance;
        }
    }

    public Cursor selectAll(){
        return getWritableDatabase().rawQuery(("SELECT * FROM weights"), null);
    }

    public Cursor selectResults(String exercise){
        return getWritableDatabase().rawQuery(("SELECT * FROM weights WHERE exercise = '" + exercise + "' ORDER BY _id DESC"), null);
    }

    public void insert(Weights insertion){
        ContentValues value = new ContentValues();
        value.put("exercise", insertion.getExercise());
        value.put("setA", insertion.getSetA());
        value.put("setB", insertion.getSetB());
        value.put("setC", insertion.getSetC());
        value.put("setD", insertion.getSetD());

        getWritableDatabase().insert("weights", null, value);
    }

    public void deleteCardio(long id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from weights where _id ='" + id + "'");
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
        sqLiteDatabase.execSQL("drop table weights");
        onCreate(sqLiteDatabase);
    }
}