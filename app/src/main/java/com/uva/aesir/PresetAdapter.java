package com.uva.aesir;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class PresetAdapter extends ResourceCursorAdapter {
    public PresetAdapter(Context context, Cursor cursor){
        super(context, R.layout.preset_entry, cursor);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.preset_title);

        title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }
}
