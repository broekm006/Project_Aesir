package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;


public class SpecificActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> weights = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent = getIntent();

        TextView name = findViewById(R.id.specific_name);
        TextView description = findViewById(R.id.specific_description);
        ImageView image = findViewById(R.id.specific_image);

        name.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
                Picasso.get().load(intent.getStringExtra("image"))
                .resize(1000,1000)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);

        Spinner one = (Spinner) findViewById(R.id.set_1);
        Spinner two = (Spinner) findViewById(R.id.set_2);
        Spinner three = (Spinner) findViewById(R.id.set_3);
        Spinner four = (Spinner) findViewById(R.id.set_4);

        for(int i = 0; i < 40; i++){ // temp value, must eventually hold more than just 40, but gets ugly if list gets to long
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
    }

    public void OnButtonClick(View view){
        startActivity(new Intent(this, TimerActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String txt = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SpecificActivity.this, ExerciseActivity.class));
    }

    // voor spinner inhoud zie:
    // https://developer.android.com/guide/topics/ui/controls/spinner#java
    // heeft eigen adapter nodig

    // voeg plus button toe zodat deze gelijk aan een lijst kan worden toegevoegd
}
