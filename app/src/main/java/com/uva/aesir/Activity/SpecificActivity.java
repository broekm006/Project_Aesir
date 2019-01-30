/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to show the name, description and image (if available) about an exercise.
 ** Beside the information it is also possible to the exercise to an existing list.
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;
import com.uva.aesir.Database.ListNameDatabase;
import com.uva.aesir.Model.Preset;
import com.uva.aesir.Database.PresetDatabase;
import com.uva.aesir.R;


public class SpecificActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String getExerciseName, txt;
    PresetDatabase getListNames;
    ListNameDatabase db;
    Spinner spin;
    Button btnListName;
    TextView explain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent = getIntent();

        // setup databases
        getListNames = PresetDatabase.getInstance(getApplicationContext());
        db = ListNameDatabase.getInstance(getApplicationContext());

        TextView name = findViewById(R.id.specific_name);
        TextView description = findViewById(R.id.specific_description);
        ImageView image = findViewById(R.id.specific_image);

        getExerciseName = intent.getStringExtra("title");
        name.setText(getExerciseName);
        description.setText(intent.getStringExtra("description"));
        Picasso.get().load(intent.getStringExtra("image"))
                .resize(400, 600)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);
    }


    // hold value of spinner item(s) that are selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        txt = adapterView.getItemAtPosition(i).toString();
    }


    // required for working of spinners
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // does nothing
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(SpecificActivity.this, ExerciseActivity.class));
    }


    // get action bar button "add new list"
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_list, menu);
        return true;
    }


    // on button click add new spinner + button to add the exercise to a existing list
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_list:
                spin = findViewById(R.id.spinner_get_list_name);
                spin.setVisibility(View.VISIBLE);

                btnListName = findViewById(R.id.button_get_list_name);
                btnListName.setVisibility(View.VISIBLE);

                explain = findViewById(R.id.textView12);
                explain.setVisibility(View.VISIBLE);

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getNameList());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapter);
                spin.setOnItemSelectedListener(this);

                // force screen to new spinner
                ScrollView scroller = (ScrollView) this.findViewById(R.id.scroller);
                scroller.fullScroll(ScrollView.FOCUS_DOWN);
        }
        return (super.onOptionsItemSelected(item));
    }


    // on click get data from spinner and add this to the database. Also show toast message for clarification
    public void onOkClick(View view) {
        Preset connectToList = new Preset(txt, getExerciseName);
        getListNames.insert(connectToList);

        String string = "Exercise: " + getExerciseName + " is added to: " + txt;
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
        toast.show();

        spin.setVisibility(View.GONE);
        btnListName.setVisibility(View.GONE);
        explain.setVisibility(View.GONE);
    }


    // get a list of title (names) to fill the spinner dropdowns
    public List<String> getNameList() {
        List<String> nameList = new ArrayList<String>();

        db.getReadableDatabase();
        Cursor cursor = db.selectDistinctNames();

        if (cursor.moveToFirst()) {
            do {
                nameList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return nameList;
    }
}