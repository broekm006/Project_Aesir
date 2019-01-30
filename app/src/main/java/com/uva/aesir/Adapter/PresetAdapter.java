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

public class PresetAdapter extends ResourceCursorAdapter {
    public PresetAdapter(Context context, Cursor cursor) {
        super(context, R.layout.preset_entry, cursor);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.preset_title);
        title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }
}
