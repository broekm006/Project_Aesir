/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to fill a listview with the correct data / style
 **
 ***/

package com.uva.aesir.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.uva.aesir.R;

public class ExerciseAdapterdb extends ResourceCursorAdapter {
    public ExerciseAdapterdb(Context context, Cursor cursor) {
        super(context, R.layout.json_db_entry, cursor);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.json_db_name);
        title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }
}
