package com.uva.aesir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class GetJSONActivity extends AppCompatActivity implements ExerciseRequest.Callback {
    JsonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_get_json);
            ExerciseRequest x = new ExerciseRequest(this);
            x.getExercise(this);

            db = JsonDatabase.getInstance(getApplicationContext());
            }

    public void OnButtonClick(View view){
        //startActivity(new Intent(this, SpecificActivity.class));
        System.out.println(db.selectAll());
    }


    @Override
    public void gotExercise(ArrayList<Exercise> exercise) {
        for(int i = 0; i < exercise.size(); i++){
            db.insert(exercise.get(i));
        }
    }

    @Override
    public void gotExerciseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
