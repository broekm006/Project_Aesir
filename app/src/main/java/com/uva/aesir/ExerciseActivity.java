package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ExerciseActivity extends AppCompatActivity {
    JsonDatabase db;
    ExerciseAdapterdb adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        db = JsonDatabase.getInstance(getApplicationContext());
        adapter = new ExerciseAdapterdb(this, db.selectAll());

        ListView listView = (ListView) findViewById(R.id.exercises_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    public void OnButtonClick(View view) {
        startActivity(new Intent(this, SpecificActivity.class));
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor one = (Cursor) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(ExerciseActivity.this, SpecificActivity.class);
            intent.putExtra("title", one.getString(one.getColumnIndex("title")));
            intent.putExtra("description", one.getString(one.getColumnIndex("description")));


            startActivity(intent);
        }
    }

    public void updateData() {
        Cursor second = db.selectAll();
        adapter.swapCursor(second);
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ExerciseActivity.this, MainActivity.class));
    }
}
