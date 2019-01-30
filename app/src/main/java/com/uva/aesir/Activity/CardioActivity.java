/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used for the Cardio screen. Here it is possible to select an activity, enter
 ** the data (km, speed and time) and store this in the database for later use.
 ***/

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

        walk = findViewById(R.id.cardio_walking);
        run = findViewById(R.id.cardio_running);
        cycle = findViewById(R.id.cardio_cycling);
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

            String kmText = km.getText().toString().trim();
            String speedText = speed.getText().toString().trim();
            String timeText = time.getText().toString().trim();

            // check if some data is actually entered, if not show toast message
            if (TextUtils.isEmpty(kmText) || kmText.length() == 0 || kmText.equals("") || kmText == null) {
                String warning = "Please enter your traveled distance in whole km";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else if (TextUtils.isEmpty(timeText) || timeText.length() == 0 || timeText.equals("") || timeText == null) {
                String warning = "Please enter the time you spend on the activity in minutes";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else if (TextUtils.isEmpty(speedText) || speedText.length() == 0 || speedText.equals("") || speedText == null) {
                String warning = "Please enter your average speed in whole km";
                Toast toast = Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG);
                toast.show();
            } else {

                // insert data to database (table Cardio)
                Cardio cardio = new Cardio(Integer.parseInt(km.getText().toString()), Integer.parseInt(speed.getText().toString()),
                        Integer.parseInt(time.getText().toString()), activity);
                db.insert(cardio);

                startActivity(new Intent(CardioActivity.this, MainActivity.class));
            }
        }
    }


    // when button is clicked highlight specific button
    public void onButtonSelect(View view) {
        switch (view.getId()) {
            case R.id.cardio_walking:
                walk.setImageResource(R.drawable.walkin_selected);
                run.setImageResource(R.drawable.runnin);
                cycle.setImageResource(R.drawable.cycling);
                check = true;
                activity = "Walking";
                break;

            case R.id.cardio_running:
                walk.setImageResource(R.drawable.walkin);
                run.setImageResource(R.drawable.runnin_selected);
                cycle.setImageResource(R.drawable.cycling);
                check = true;
                activity = "Running";
                break;

            case R.id.cardio_cycling:
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