package com.uva.aesir.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.uva.aesir.R;

public class PresetAdapterExercises extends ResourceCursorAdapter {
    public PresetAdapterExercises(Context context, Cursor cursor) {
        super(context, R.layout.preset_entry, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.preset_title);
        title.setText(cursor.getString(cursor.getColumnIndex("exercise_name")));
    }
}
