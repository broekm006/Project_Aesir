package com.uva.aesir;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class NewListActivity extends AppCompatActivity {
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
        //parentLinearLayout.setLayoutTransition(null);
        parentLinearLayout.removeView((View) v.getParent());
        //parentLinearLayout.removeAllViews();
        System.out.println(v.getParent());
    }
}
