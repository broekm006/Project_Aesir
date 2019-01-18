package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Preset_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_detail);

        Intent intent = getIntent();
        String titleRetrieved = (String) intent.getSerializableExtra("title");
        String exerciseRetrieved = (String) intent.getSerializableExtra("exercise");

        TextView title = findViewById(R.id.detail_title);
        TextView exercise = findViewById(R.id.detail_exercise);

        title.setText(titleRetrieved);
        exercise.setText(exerciseRetrieved);
    }
}
