package com.uva.aesir;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener { //implements AdapterView.OnItemSelectedListener
    String txt;
    Button btn;
    Spinner exercise;
    private LinearLayout parentLinearLayout;
    JsonDatabase exercise_listy;
    PresetDatabase db;
    ArrayList attempt400 = new ArrayList();
    //String[] sets = {"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        exercise_listy = JsonDatabase.getInstance(getApplicationContext());
        db = PresetDatabase.getInstance(getApplicationContext());

        // add dynamic
        parentLinearLayout = (LinearLayout) findViewById(R.id.new_list_linear);

        // grey out submit button untill name is given > HOW?!?!?
        btn = (Button) findViewById(R.id.Submit_newlist);
        //btn.setEnabled(false);

//        Spinner choose_exercise = (Spinner) findViewById(R.id.newlist_exercise);
//        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sets);
//        choose_exercise.setAdapter(adapterE);
//        choose_exercise.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        txt = adapterView.getItemAtPosition(i).toString();
        if (txt.isEmpty() || txt == null || txt.equals("")) {
        } else {
            attempt400.add(txt); // i know this is not a perfect way, but will be improved in a later stage (if time)
        }
//        PresetListItem presetListItem = new PresetListItem(txt);
//        db.insertListItem(presetListItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addEntry(View view) {
        TextView title = findViewById(R.id.newlist_title);

        for (int i = 0; i < attempt400.size(); i++) {
            Preset new_preset = new Preset(title.getText().toString(), attempt400.get(i).toString());
            PresetDatabase db = PresetDatabase.getInstance(this);
            db.insert(new_preset);
        }

        ListName newName = new ListName(title.getText().toString());
        db.insertTitle(newName);

        startActivity(new Intent(this, PresetActivity.class));
    }

    public List<String> getNameExercise() {
//        EditText title = findViewById(R.id.newlist_title);
//        String titled = title.getText().toString().trim();

        List<String> nameExercise = new ArrayList<String>();

        String query = "SELECT title FROM exercises";

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

    public void addNewField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_list_entry, null, false);

        exercise = (Spinner) rowView.findViewById(R.id.newlist_exercise2);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getNameExercise());
        exercise.setAdapter(adapter);
        exercise.setOnItemSelectedListener(this);

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDeleteclick(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        System.out.println("test" + v.getParent());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewListActivity.this, PresetActivity.class));
    }
}
