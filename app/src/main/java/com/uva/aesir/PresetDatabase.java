package com.uva.aesir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class PresetDatabase extends SQLiteOpenHelper {
    private static PresetDatabase instance;
    SQLiteDatabase sqLiteDatabase;

    public PresetDatabase(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static PresetDatabase getInstance(Context context){
        if (instance == null){
            return instance = new PresetDatabase(context, "com.uva.aesir", null, 1);
        }

        else{
            return instance;
        }
    }

    public Cursor selectAll(){
        return getWritableDatabase().rawQuery(("SELECT * FROM presets"), null);
    }

    public void insert(Preset insertion){
        ContentValues value = new ContentValues();
        value.put("Exercise_name", insertion.getExercise_name());
        value.put("title", insertion.getTitle());
        value.put("numberOfTimes", insertion.getNumberOfTimes());
        getWritableDatabase().insert("presets", null, value);
    }

    public void delete(long id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from presets where _id='" + id + "'");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table presets (_id INTEGER PRIMARY KEY, " +
                "Exercise_name TEXT, title TEXT, numberOfTimes TEXT)");

        sqLiteDatabase.execSQL("create table exercises (_id INTEGER PRIMARY KEY, " +
                "idex TEXT, title TEXT, description TEXT, category TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table presets");
        onCreate(sqLiteDatabase);
    }
}
