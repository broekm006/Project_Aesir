package com.uva.aesir;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardioActivity extends AppCompatActivity {
    Button walk, run, cycle;
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
            // insert data to database (new table Cardio)
            EditText km = findViewById(R.id.Cardio_edit_km);
            EditText speed = findViewById(R.id.Cardio_edit_speed);
            EditText time = findViewById(R.id.Cardio_edit_time);

            Cardio cardio = new Cardio(Integer.parseInt(km.getText().toString()),Integer.parseInt(speed.getText().toString()),Integer.parseInt(time.getText().toString()), activity);
            db.insert(cardio);

            startActivity(new Intent(CardioActivity.this, MainActivity.class));
        }
    }

    public void onButtonSelect(View view) {
        switch (view.getId()) { // check statement getID is correct
            case R.id.Cardio_walking:
                walk.setBackgroundColor(Color.parseColor("#9dada0"));
                run.setBackgroundColor(Color.parseColor("#00000000"));
                cycle.setBackgroundColor(Color.parseColor("#00000000"));
                check = true;
                activity = "Walking";
                break;

            case R.id.Cardio_running:
                walk.setBackgroundColor(Color.parseColor("#00000000"));
                run.setBackgroundColor(Color.parseColor("#9dada0"));
                cycle.setBackgroundColor(Color.parseColor("#00000000"));
                check = true;
                activity = "Running";
                break;

            case R.id.Cardio_cycling:
                walk.setBackgroundColor(Color.parseColor("#00000000"));
                run.setBackgroundColor(Color.parseColor("#00000000"));
                cycle.setBackgroundColor(Color.parseColor("#9dada0"));
                check = true;
                activity = "Cycling";
                break;

            default:
                check = false;
        }
    }


}
