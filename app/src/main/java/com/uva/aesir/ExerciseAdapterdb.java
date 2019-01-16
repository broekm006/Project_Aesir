package com.uva.aesir;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class ExerciseAdapterdb extends ResourceCursorAdapter {

    public ExerciseAdapterdb(Context context, Cursor cursor){
        super(context, R.layout.json_db_entry, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.json_db_name);

        title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }
}
