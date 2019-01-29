package com.uva.aesir.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.uva.aesir.Adapter.PresetAdapterExercises;
import com.uva.aesir.Database.PresetDatabase;
import com.uva.aesir.R;

public class Preset_detail extends AppCompatActivity {
    PresetDatabase db;
    PresetAdapterExercises adapter;
    String titleRetrieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_detail);

        Intent intent = getIntent();
        titleRetrieved = (String) intent.getSerializableExtra("title");

        // setup db + adapter
        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        // setup listview with adapter + click listeners
        ListView listView = findViewById(R.id.detail_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new Preset_detail.ListViewLongClickListener());

        TextView title = findViewById(R.id.detail_title);
        title.setText(titleRetrieved);
    }


    // on click get selected data for the next activity
    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor one = (Cursor) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(Preset_detail.this, PresetExercise.class);
            intent.putExtra("exercise_name", one.getString(one.getColumnIndex("exercise_name")));
            intent.putExtra("title", one.getString(one.getColumnIndex("title")));

            one.close();
            startActivity(intent);
        }
    }


    // on long click delete selected data
    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long position = cursor.getLong(cursor.getColumnIndex("_id"));

            db.delete(position);

            updateData();

            cursor.close();
            return true;
        }
    }


    // refresh screen without the deleted value
    public void updateData() {
        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        ListView listView = (ListView) findViewById(R.id.detail_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }


    // on resume rebuild layout with correct data
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        titleRetrieved = (String) intent.getSerializableExtra("title");

        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        ListView listView = findViewById(R.id.detail_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PresetActivity.class));
    }


}
