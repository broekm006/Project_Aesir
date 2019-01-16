package com.uva.aesir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class JsonDatabase extends SQLiteOpenHelper {
    private static JsonDatabase instance;
    SQLiteDatabase sqLiteDatabase;

    public JsonDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static JsonDatabase getInstance(Context context){
        if (instance == null){
            return instance = new JsonDatabase(context, "com.uva.aesir", null, 1);
        }

        else{
            return instance;
        }
    }

    public Cursor selectAll(){
        return getWritableDatabase().rawQuery(("SELECT * FROM exercises"), null);
    }


    public void insert(Exercise insertion){

        ContentValues value = new ContentValues();
        value.put("idex", insertion.getIdex());
        value.put("title", insertion.getName());
        value.put("description", insertion.getDescription());
        value.put("category", insertion.getCategorie());

        Cursor cursor = null;
        cursor = getWritableDatabase().rawQuery(("SELECT idex FROM exercises WHERE idex =" + insertion.getIdex()), null);

        if (cursor.getCount() > 0){

        }
        else{
            getWritableDatabase().insert("exercises", null, value);
        }

        cursor.close();

    }

    public void delete(long id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from exercises where _id ='" + id + "'");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table exercises (_id INTEGER PRIMARY KEY, idex TEXT, title TEXT, description TEXT, category TEXT)");
        sqLiteDatabase.execSQL("create table presets (_id INTEGER PRIMARY KEY, Exercise_name TEXT, title TEXT, numberOfTimes TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table exercises");
        onCreate(sqLiteDatabase);
    }
}
