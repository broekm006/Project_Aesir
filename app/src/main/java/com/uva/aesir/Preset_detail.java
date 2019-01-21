package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Preset_detail extends AppCompatActivity {
    PresetDatabase db;
    PresetAdapterExercises adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_detail);

        Intent intent = getIntent();
        String titleRetrieved = (String) intent.getSerializableExtra("title");

        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapterExercises(this, db.selectDiscinctExercises(titleRetrieved));

        ListView listView = findViewById(R.id.detail_list);
        listView.setAdapter(adapter);

        TextView exercise = findViewById(R.id.detail_exercise);
        exercise.setText("");

        TextView title = findViewById(R.id.detail_title);
        title.setText(titleRetrieved);


    }


}
