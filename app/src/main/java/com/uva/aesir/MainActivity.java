package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnButtonClick(View view){
        startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
    }

    public void onButtonClickResults(View view){
        startActivity(new Intent(MainActivity.this, ResultsActivity.class));
    }

    public void onButtonClickPresets(View view) {
        startActivity(new Intent(MainActivity.this, PresetActivity.class));
    }
}
