/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to display a list of exercises gathered from the database
 **
 ***/

package com.uva.aesir.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uva.aesir.Adapter.ExerciseAdapterdb;
import com.uva.aesir.Database.JsonDatabase;
import com.uva.aesir.R;

public class ExerciseActivity extends AppCompatActivity {
    JsonDatabase db;
    ExerciseAdapterdb adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // setup database + adapter for listview
        db = JsonDatabase.getInstance(getApplicationContext());
        adapter = new ExerciseAdapterdb(this, db.selectAll());

        // setup listview to get database information through the adapter
        ListView listView = (ListView) findViewById(R.id.exercises_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }


    public void OnButtonClick(View view) {
        startActivity(new Intent(this, SpecificActivity.class));
    }


    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        // on click send the title, description and image information to the next activity
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor one = (Cursor) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(ExerciseActivity.this, SpecificActivity.class);
            intent.putExtra("title", one.getString(one.getColumnIndex("title")));
            intent.putExtra("description", one.getString(one.getColumnIndex("description")));
            intent.putExtra("image", one.getString(one.getColumnIndex("imgUrl")));

            one.close();
            startActivity(intent);
        }
    }


    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ExerciseActivity.this, MainActivity.class));
    }
}
