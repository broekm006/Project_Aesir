package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PresetExercise extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> weights = new ArrayList<String>();
    PresetDatabase db;
    WeightsDatabase database;
    Spinner one;
    Spinner two;
    Spinner three;
    Spinner four;

    String exerciseName, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_exercise);
        database = WeightsDatabase.getInstance(getApplicationContext());
        db = PresetDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        exerciseName = intent.getStringExtra("exercise_name");
        title = intent.getStringExtra("title");

        Cursor cursor = db.selectExercises(exerciseName);
        cursor.moveToFirst();

        TextView txt = findViewById(R.id.entry_title);
        TextView description = findViewById(R.id.entry_description);
        ImageView image = findViewById(R.id.entry_image);

        txt.setText(cursor.getString(cursor.getColumnIndex("title")));
        description.setText(cursor.getString(cursor.getColumnIndex("description")));
        Picasso.get().load(cursor.getString(cursor.getColumnIndex("imgUrl")))
                .resize(400, 600)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

        one = (Spinner) findViewById(R.id.set_1);
        two = (Spinner) findViewById(R.id.set_2);
        three = (Spinner) findViewById(R.id.set_3);
        four = (Spinner) findViewById(R.id.set_4);

        for (int i = 0; i < 40; i++) { // temp value, must eventually hold more than just 40, but gets ugly if list gets to long
            weights.add("" + i + "");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weights);

        one.setAdapter(adapter);
        one.setOnItemSelectedListener(this);

        two.setAdapter(adapter);
        two.setOnItemSelectedListener(this);

        three.setAdapter(adapter);
        three.setOnItemSelectedListener(this);

        four.setAdapter(adapter);
        four.setOnItemSelectedListener(this);

        cursor.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String txt = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickSubmit(View view){
        Weights weights = new Weights(Integer.parseInt(one.getSelectedItem().toString()),Integer.parseInt(two.getSelectedItem().toString()),Integer.parseInt(three.getSelectedItem().toString()),Integer.parseInt(four.getSelectedItem().toString()), exerciseName);
        database.insert(weights);

        Intent intent = new Intent(this, Preset_detail.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}


// voor spinner inhoud zie:
// https://developer.android.com/guide/topics/ui/controls/spinner#java
// heeft eigen adapter nodig

// voeg plus button toe zodat deze gelijk aan een lijst kan worden toegevoegd

