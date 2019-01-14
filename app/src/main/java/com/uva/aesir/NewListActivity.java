package com.uva.aesir;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn;
    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        // add dynamic
        parentLinearLayout = (LinearLayout) findViewById(R.id.new_list_linear);

        // grey out submit button untill atleast ONE exercise is chosen
        btn = (Button) findViewById(R.id.Submit_newlist);
        //btn.setEnabled(false);

        // create working spinners
        // 1 for number of sets (1 - 4)
        // 1 for list of all exercises > + be able to add more than 1 exercise

        Spinner choose_sets = (Spinner) findViewById(R.id.spinner);
        choose_sets.setOnItemSelectedListener(this);

        List<String> sets = new ArrayList<String>();
        sets.add("1");
        sets.add("2");
        sets.add("3");
        sets.add("4");
        sets.add("5");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sets);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choose_sets.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addEntry(View view){
        TextView title = findViewById(R.id.newlist_title);
        Spinner numberOfSets = findViewById(R.id.newlist_numberOfSets);
        Spinner exercise = findViewById(R.id.newlist_exercise);

        Preset new_preset = new Preset(title.getText().toString(), numberOfSets.toString(), exercise.toString());
        PresetDatabase db = PresetDatabase.getInstance(this);
        db.insert(new_preset);

        startActivity(new Intent(this, PresetActivity.class));

    }

    public void addNewField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_list_entry, null);

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDeleteclik(View v){
        parentLinearLayout.removeView((View) v.getParent());
        System.out.println(v.getParent());
    }
}
