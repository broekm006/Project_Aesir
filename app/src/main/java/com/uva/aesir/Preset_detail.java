package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        ListView listView = findViewById(R.id.detail_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());

        TextView title = findViewById(R.id.detail_title);
        title.setText(titleRetrieved);
    }

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

    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        titleRetrieved = (String) intent.getSerializableExtra("title");

        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        ListView listView = findViewById(R.id.detail_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PresetActivity.class));
    }


}
