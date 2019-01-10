package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NewListActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

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
}
