package com.uva.aesir;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener { //implements AdapterView.OnItemSelectedListener
    Button btn;
    private LinearLayout parentLinearLayout;
    JsonDatabase exercise_listy;

    String[] sets ={"1", "2", "3", "4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        exercise_listy = JsonDatabase.getInstance(getApplicationContext());

        // add dynamic
        parentLinearLayout = (LinearLayout) findViewById(R.id.new_list_linear);

        // grey out submit button untill atleast ONE exercise is chosen
        btn = (Button) findViewById(R.id.Submit_newlist);
        //btn.setEnabled(false);

        // 1 for list of all exercises >


        Spinner choose_exercise = (Spinner) findViewById(R.id.newlist_exercise);
        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sets);
        choose_exercise.setAdapter(adapterE);
        choose_exercise.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String txt = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), txt, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addEntry(View view){
        TextView title = findViewById(R.id.newlist_title);
        //Spinner numberOfSets = findViewById(R.id.newlist_numberOfSets);
        Spinner exercise = findViewById(R.id.newlist_exercise);

        Preset new_preset = new Preset(title.getText().toString(), exercise.toString());
        PresetDatabase db = PresetDatabase.getInstance(this);
        db.insert(new_preset);

        startActivity(new Intent(this, PresetActivity.class));

    }

    public List<String> getNameExercise(){
        List<String> nameExercise = new ArrayList<String>();

        String query = "SELECT title FROM exercises";

        exercise_listy.getReadableDatabase();
        Cursor cursor = exercise_listy.selectName();

        if (cursor.moveToFirst()){
            do{
                nameExercise.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        exercise_listy.close();

        return nameExercise;
    }

    public void addNewField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_list_entry, null);

        Spinner exercise = (Spinner) rowView.findViewById(R.id.newlist_exercise2);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getNameExercise());
        exercise.setAdapter(adapter);
        exercise.setOnItemSelectedListener(this);



        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

    }

    public void onDeleteclick(View v){
        parentLinearLayout.removeView((View) v.getParent());
        System.out.println(v.getParent());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewListActivity.this, PresetActivity.class));
    }
}
