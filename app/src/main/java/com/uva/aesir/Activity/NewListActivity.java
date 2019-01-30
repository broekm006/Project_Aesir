/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to create a new list (preset) here a dynamic field is added every time a button
 ** gets pressed and the data is stored through spinners in a arraylist into the database.
 **
 ***/

package com.uva.aesir.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.uva.aesir.Database.JsonDatabase;
import com.uva.aesir.Model.ListName;
import com.uva.aesir.Model.Preset;
import com.uva.aesir.Database.PresetDatabase;
import com.uva.aesir.R;

import java.util.ArrayList;
import java.util.List;

public class NewListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener { //implements AdapterView.OnItemSelectedListener
    String txt;
    Button btn;
    Spinner exercise;
    int INTER = 0;
    private LinearLayout parentLinearLayout;
    JsonDatabase exercise_listy;
    PresetDatabase db;
    ArrayList listOfExercises = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        exercise_listy = JsonDatabase.getInstance(getApplicationContext());
        db = PresetDatabase.getInstance(getApplicationContext());

        parentLinearLayout = (LinearLayout) findViewById(R.id.new_list_linear);
        btn = (Button) findViewById(R.id.Submit_newlist);
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


    // submit data > get data from spinners and add to database
    public void addEntry(View view) {
        EditText title = findViewById(R.id.newlist_title);
        String title_txt = title.getText().toString().trim();

        // check to see if title is empty
        if (TextUtils.isEmpty(title_txt) || title_txt == null || title_txt.equals("") || title_txt.length() == 0) {
            String warning = "Please give your workout list a name :)";
            Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
            toast.show();
        } else {

            // get values from spinners based on amount of child layouts created
            for (int w = 0; w < parentLinearLayout.getChildCount(); ++w) {
                Spinner spinner = findViewById(w);
                listOfExercises.add(spinner.getSelectedItem());
            }

            // if no exercise is added show error message
            if (listOfExercises.size() == 0) {
                String warning = "Please add at least one exercise to the list:)";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else {

                // add new exercises to the database
                for (int i = 0; i < listOfExercises.size(); i++) {
                    Preset new_preset = new Preset(title.getText().toString(), listOfExercises.get(i).toString());
                    PresetDatabase db = PresetDatabase.getInstance(this);
                    db.insert(new_preset);
                }

                ListName newName = new ListName(title.getText().toString());
                db.insertTitle(newName);

                startActivity(new Intent(this, PresetActivity.class));
            }
        }

    }


    // get a list of exercise (names) to fill the spinner dropdowns
    public List<String> getNameExercise() {
        List<String> nameExercise = new ArrayList<String>();

        exercise_listy.getReadableDatabase();
        Cursor cursor = exercise_listy.selectName();

        if (cursor.moveToFirst()) {
            do {
                nameExercise.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        exercise_listy.close();

        return nameExercise;
    }


    // add new dynamic field on button click
    public void addNewField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_list_entry, null, false);

        // set hardcoded id so data can be collected based on the id(int)
        exercise = (Spinner) rowView.findViewById(R.id.newlist_exercise2);
        exercise.setId(INTER);
        INTER += 1;

        // set adapter with the list of exercises
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getNameExercise());
        exercise.setAdapter(adapter);
        exercise.setOnItemSelectedListener(this);

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }


    // delete dynamically added layout views
    public void onDeleteclick(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        System.out.println("test" + v.getParent());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewListActivity.this, PresetActivity.class));
    }
}
