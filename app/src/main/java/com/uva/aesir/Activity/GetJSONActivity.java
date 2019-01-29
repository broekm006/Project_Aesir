package com.uva.aesir.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.uva.aesir.Model.Exercise;
import com.uva.aesir.Model.ExerciseImg;
import com.uva.aesir.Request.ExerciseImgRequest;
import com.uva.aesir.Request.ExerciseRequest;
import com.uva.aesir.Database.JsonDatabase;
import com.uva.aesir.R;

import java.util.ArrayList;

public class GetJSONActivity extends AppCompatActivity implements ExerciseRequest.Callback, ExerciseImgRequest.Callback {
    JsonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_json);
        ExerciseRequest x = new ExerciseRequest(this);
        x.getExercise(this);

        ExerciseImgRequest y = new ExerciseImgRequest(this);
        y.getExerciseImg(this);

        db = JsonDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void gotExercise(ArrayList<Exercise> exercise) {
        for (int i = 0; i < exercise.size(); i++) {
            db.insert(exercise.get(i));
        }
    }

    @Override
    public void gotExerciseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotExerciseImg(ArrayList<ExerciseImg> exerciseImgs) {
        for (int i = 0; i < exerciseImgs.size(); i++) {
            db.insertImg(exerciseImgs.get(i));
        }
    }

    @Override
    public void gotExerciseImgError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
