package com.uva.aesir.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.uva.aesir.Model.ListName;
import com.uva.aesir.Model.Preset;

public class PresetDatabase extends SQLiteOpenHelper {
    private static PresetDatabase instance;
    SQLiteDatabase sqLiteDatabase;

    public PresetDatabase(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static PresetDatabase getInstance(Context context) {
        if (instance == null) {
            return instance = new PresetDatabase(context, "com.uva.aesir", null, 1);
        } else {
            return instance;
        }
    }

    public Cursor selectAll() {
        return getWritableDatabase().rawQuery(("SELECT DISTINCT title FROM presets"), null);
    }

    public Cursor selectDiscinctExercises(String name) {
        return getWritableDatabase().rawQuery(("SELECT * FROM presets WHERE title = '" + name + "' ORDER BY exercise_name ASC "), null);
    }

    public Cursor selectExercises(String exerciseName) {
        return getWritableDatabase().rawQuery(("SELECT * FROM exercises LEFT JOIN exerciseImgs ON exercises.idex = exerciseImgs.idex WHERE title = '" + exerciseName + "'"), null);
    }

    public void insert(Preset insertion) {
        ContentValues value = new ContentValues();
        value.put("Exercise_name", insertion.getExercise_name());
        value.put("title", insertion.getListName());
        //value.put("numberOfTimes", insertion.getNumberOfTimes());
        getWritableDatabase().insert("presets", null, value);
    }

    public void insertTitle(ListName insertion) {
        ContentValues value = new ContentValues();
        value.put("title", insertion.getListName());
        getWritableDatabase().insert("listName", null, value);
    }

    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from presets where _id='" + id + "'");
    }

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
