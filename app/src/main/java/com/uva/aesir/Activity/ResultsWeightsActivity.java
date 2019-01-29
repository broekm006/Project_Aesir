package com.uva.aesir.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uva.aesir.R;
import com.uva.aesir.Adapter.ResultsWeightsAdapter;
import com.uva.aesir.Database.WeightsDatabase;

public class ResultsWeightsActivity extends AppCompatActivity {
    WeightsDatabase db;
    ResultsWeightsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_weights);

        // setup database + adapter
        db = WeightsDatabase.getInstance(getApplicationContext());
        adapter = new ResultsWeightsAdapter(this, db.selectAll());

        // setup listview with adapter & click listener
        ListView listView = findViewById(R.id.result_weight_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ResultsListActivity.class));
    }


    // on click get selected data for the next activity
    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor one = (Cursor) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(ResultsWeightsActivity.this, ResultsActivity.class);
            intent.putExtra("exerciseName", one.getString(one.getColumnIndex("exercise")));

            one.close();
            startActivity(intent);
        }
    }


}
