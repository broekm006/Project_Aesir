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

        db = ListNameDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapter(this, db.selectAll());

        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_list:
                startActivity(new Intent(this, NewListActivity.class));
        }
        return (super.onOptionsItemSelected(item));
    }


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
