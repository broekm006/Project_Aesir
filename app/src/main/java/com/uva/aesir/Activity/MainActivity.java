/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is the main menu for the application. From here you can update the db with the latest
 ** exercises and click the navigation buttons to navigate the app.
 **
 ***/

package com.uva.aesir.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.uva.aesir.Model.Exercise;
import com.uva.aesir.Model.ExerciseImg;
import com.uva.aesir.Request.ExerciseImgRequest;
import com.uva.aesir.Request.ExerciseRequest;
import com.uva.aesir.Database.JsonDatabase;
import com.uva.aesir.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExerciseRequest.Callback, ExerciseImgRequest.Callback {
    JsonDatabase db;
    ProgressBar progressBar;
    int count = 0;
    final int maxNumberOfPages = 17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar3);
    }


    // get new action bar button "update"
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    // manipulate action bar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:

                // on click show progress bar && lock the screen until download is complete
                progressBar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                ExerciseRequest x = new ExerciseRequest(this);
                x.getExercise(this);

                ExerciseImgRequest y = new ExerciseImgRequest(this);
                y.getExerciseImg(this);

                db = JsonDatabase.getInstance(getApplicationContext());

            case R.id.info:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                String part1 = "To see what activities are available click on Exercises.";
                String part2 = "To enter data to calculate cardio workouts go to Cardio.";
                String part3 = "To make your own workout list, and start working out click on Preset";
                String part4 = "To get an overview of your progress click on Stats. Here you will see both weight lifting and cardio";

                builder.setCancelable(true);
                builder.setTitle("How to:");
                builder.setMessage(part1 + "\n\n" + part2 + "\n\n" + part3 + "\n\n" + part4);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
        }

        return (super.onOptionsItemSelected(item));
    }


    // go to exercises
    public void OnButtonClick(View view) {
        startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
    }


    // go to cardio input
    public void OnButtonClickCardio(View view) {
        startActivity(new Intent(MainActivity.this, CardioActivity.class));
    }


    // go to results
    public void onButtonClickResults(View view) {
        startActivity(new Intent(MainActivity.this, ResultsListActivity.class));
    }


    // go to presets
    public void onButtonClickPresets(View view) {
        startActivity(new Intent(MainActivity.this, PresetActivity.class));
    }


    // retrieve exercises from json && store results in the database
    @Override
    public void gotExercise(ArrayList<Exercise> exercise) {
        for (int i = 0; i < exercise.size(); i++) {
            db.insert(exercise.get(i));
        }
        count += 1;

        // if max pages is reached unlock the activity for touch events
        if (count > maxNumberOfPages) {
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            db.deleteEmptyExercises();
        }
    }


    // if error show message through toast
    @Override
    public void gotExerciseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    // retrieve image urls from json && store results in the database
    @Override
    public void gotExerciseImg(ArrayList<ExerciseImg> exerciseImgs) {
        for (int i = 0; i < exerciseImgs.size(); i++) {
            db.insertImg(exerciseImgs.get(i));
        }
    }


    // if error show message through toast
    @Override
    public void gotExerciseImgError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    // when back is pressed exit the application
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
