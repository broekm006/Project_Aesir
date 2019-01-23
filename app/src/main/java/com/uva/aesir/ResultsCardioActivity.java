package com.uva.aesir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsCardioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_cardio);

        TextView calories = findViewById(R.id.Calories_result);
        TextView distance = findViewById(R.id.Distance_result);

        calories.setText("100");
        distance.setText("1,44 KM!!");
    }
}
