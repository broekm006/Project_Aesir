/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is to display all existing lists and allow the user to create a new one
 **
 ***/

package com.uva.aesir.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uva.aesir.Database.ListNameDatabase;
import com.uva.aesir.Adapter.PresetAdapter;
import com.uva.aesir.R;

public class PresetActivity extends AppCompatActivity {
    ListNameDatabase db;
    PresetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset);

        // setup db + adapter
        db = ListNameDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapter(this, db.selectAll());

        // setup listview for adapter + click listeners
        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }


    // get action menu button "add new list"
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_list, menu);
        return true;
    }


    // on action bar button click go to new list activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_list:
                startActivity(new Intent(this, NewListActivity.class));
        }
        return (super.onOptionsItemSelected(item));
    }


    // on click get selected data for the next activity
    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor anotherOne = (Cursor) adapterView.getItemAtPosition(i);
            anotherOne.moveToPosition(i);

            Intent intent = new Intent(PresetActivity.this, Preset_detail.class);
            intent.putExtra("title", anotherOne.getString(anotherOne.getColumnIndex("title")));

            anotherOne.close();
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


    // swap cursor does not work as intented so work around is recreating the data
    public void updateData() {
        db = ListNameDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapter(this, db.selectAll());

        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }


    // recreate the data
    public void onResume() {
        super.onResume();
        db = ListNameDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapter(this, db.selectAll());

        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(PresetActivity.this, MainActivity.class));
    }
}
