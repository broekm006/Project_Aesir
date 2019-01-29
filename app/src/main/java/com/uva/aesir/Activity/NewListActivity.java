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
    ArrayList attempt400 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        exercise_listy = JsonDatabase.getInstance(getApplicationContext());
        db = PresetDatabase.getInstance(getApplicationContext());

        // add dynamic
        parentLinearLayout = (LinearLayout) findViewById(R.id.new_list_linear);

        btn = (Button) findViewById(R.id.Submit_newlist);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        txt = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing
    }

    public void addEntry(View view) {
        EditText title = findViewById(R.id.newlist_title);

        String title_txt = title.getText().toString().trim();
        if (TextUtils.isEmpty(title_txt) || title_txt == null || title_txt.equals("") || title_txt.length() == 0) {
            String warning = "Please give your workout list a name :)";
            Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
            toast.show();
        } else {
            for (int w = 0; w < parentLinearLayout.getChildCount(); ++w) {
                Spinner victory = findViewById(w);
                attempt400.add(victory.getSelectedItem());
            }

            if (attempt400.size() == 0) {
                String warning = "Please add at least one exercise to the list:)";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else {
                for (int i = 0; i < attempt400.size(); i++) {
                    Preset new_preset = new Preset(title.getText().toString(), attempt400.get(i).toString());
                    PresetDatabase db = PresetDatabase.getInstance(this);
                    db.insert(new_preset);
                }

                ListName newName = new ListName(title.getText().toString());
                db.insertTitle(newName);

                startActivity(new Intent(this, PresetActivity.class));
            }
        }

    }

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

    public void addNewField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_list_entry, null, false);

        exercise = (Spinner) rowView.findViewById(R.id.newlist_exercise2);
        exercise.setId(INTER);
        INTER += 1;
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
