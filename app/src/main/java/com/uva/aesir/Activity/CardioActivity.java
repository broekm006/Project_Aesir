/*
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used for the Cardio screen. Here it is possible to select an activity, enter
 ** the data (km, speed and time) and store this in the database for later use.
 **/

package com.uva.aesir.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.uva.aesir.Model.Cardio;
import com.uva.aesir.Database.CardioDatabase;
import com.uva.aesir.R;

public class CardioActivity extends AppCompatActivity {
    ImageButton walk, run, cycle;
    Boolean check = false;
    CardioDatabase db;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        db = CardioDatabase.getInstance(getApplicationContext());

        walk = findViewById(R.id.Cardio_walking);
        run = findViewById(R.id.Cardio_running);
        cycle = findViewById(R.id.Cardio_cycling);
    }

    public void onSubmit(View view) {

        // check if button with activity is pressed
        if (check == false) {
            String warning = "Please choose an activity: Walking, Running or Cycling";
            Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
            toast.show();
        } else {

            // get field + string information
            EditText km = findViewById(R.id.Cardio_edit_km);
            EditText speed = findViewById(R.id.Cardio_edit_speed);
            EditText time = findViewById(R.id.Cardio_edit_time);

            String km_text = km.getText().toString().trim();
            String speed_text = speed.getText().toString().trim();
            String time_text = time.getText().toString().trim();

            // check if some data is actually entered
            if (TextUtils.isEmpty(km_text) || km_text.length() == 0 || km_text.equals("") || km_text == null) {
                String warning = "Please enter your traveled distance in whole km";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else if (TextUtils.isEmpty(time_text) || time_text.length() == 0 || time_text.equals("") || time_text == null) {
                String warning = "Please enter the time you spend on the activity in minutes";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else if (TextUtils.isEmpty(speed_text) || speed_text.length() == 0 || speed_text.equals("") || speed_text == null) {
                String warning = "Please enter your average speed in whole km";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else {

                // insert data to database (new table Cardio)
                Cardio cardio = new Cardio(Integer.parseInt(km.getText().toString()), Integer.parseInt(speed.getText().toString()), Integer.parseInt(time.getText().toString()), activity);
                db.insert(cardio);

                startActivity(new Intent(CardioActivity.this, MainActivity.class));
            }
        }
    }

    public void onButtonSelect(View view) {

        // when button is clicked highlight specific button
        switch (view.getId()) {
            case R.id.Cardio_walking:
                walk.setImageResource(R.drawable.walkin_selected);
                run.setImageResource(R.drawable.runnin);
                cycle.setImageResource(R.drawable.cycling);
                check = true;
                activity = "Walking";
                break;

            case R.id.Cardio_running:
                walk.setImageResource(R.drawable.walkin);
                run.setImageResource(R.drawable.runnin_selected);
                cycle.setImageResource(R.drawable.cycling);
                check = true;
                activity = "Running";
                break;

            case R.id.Cardio_cycling:
                walk.setImageResource(R.drawable.walkin);
                run.setImageResource(R.drawable.runnin);
                cycle.setImageResource(R.drawable.cycling_selected);
                check = true;
                activity = "Cycling";
                break;

            default:
                check = false;
        }
    }


}