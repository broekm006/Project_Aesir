package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ExerciseRequest x = new ExerciseRequest(this);
        x.getExercise(this);
    }

    public void OnButtonClick(View view){
        startActivity(new Intent(this, SpecificActivity.class));
    }


    @Override
    public void gotExercise(ArrayList<Exercise> exercise) {
        ExerciseAdapter adapter = new ExerciseAdapter(this, R.layout.exercise_entry, exercise);
        ListView listView = (ListView) findViewById(R.id.exercises_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    @Override
    public void gotExerciseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Exercise exercise = (Exercise) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(ExerciseActivity.this, SpecificActivity.class);
            intent.putExtra("name", exercise.getName());
            intent.putExtra("description", exercise.getDescription());

            startActivity(intent);
        }
    }
}
