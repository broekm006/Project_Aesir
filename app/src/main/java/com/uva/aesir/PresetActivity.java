package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PresetActivity extends AppCompatActivity {
    PresetDatabase db;
    PresetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset);

        db = PresetDatabase.getInstance(getApplicationContext());
        adapter = new PresetAdapter(this, db.selectAll());

        ListView listView = (ListView) findViewById(R.id.preset_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        listView.setOnItemLongClickListener(new ListViewLongClickListener());

    }

    public void OnButtonClick(View view){
        startActivity(new Intent(this,
                                NewListActivity.class));
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor anotherOne = (Cursor) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(PresetActivity.this, Preset_detail.class);
            intent.putExtra("title", anotherOne.getString(anotherOne.getColumnIndex("title")));
            //intent.putExtra("exercise", anotherOne.getString(anotherOne.getColumnIndex("exercise")));
            //intent.putExtra("numberOfSets", anotherOne.getString(anotherOne.getColumnIndex("numberOfSets")));

            startActivity(intent);
        }
    }

    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long position = cursor.getLong(cursor.getColumnIndex("_id"));

            db.delete(position);

            updateData();
            return true;
        }
    }

    public void updateData(){
        Cursor second_cursor = db.selectAll();
        adapter.swapCursor(second_cursor);
    }

    public void onResume(){
        super.onResume();
    }
}
