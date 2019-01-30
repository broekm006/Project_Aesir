/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to display a pie chart that shows that amount of calories burned. Also this
 ** file calculates the amount of calories burned every minute / total.
 ***/

package com.uva.aesir.Activity;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;
import com.uva.aesir.Database.CardioDatabase;
import com.uva.aesir.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ResultsCardioActivity extends AppCompatActivity {
    int METS, caloriesPerMinute = 0;
    CardioDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_cardio);

        // setup database
        db = CardioDatabase.getInstance(getApplicationContext());
        Cursor speed = db.selectAll();
        speed.moveToFirst();

        TextView activity = findViewById(R.id.activity_title);
        TextView calories = findViewById(R.id.calories_result);
        TextView caloriesMinutes = findViewById(R.id.calories_minute);
        TextView distance = findViewById(R.id.distance_result);

        activity.setText(String.valueOf(speed.getString(speed.getColumnIndex("activity"))));
        calories.setText(String.valueOf(calcCalories(speed.getInt(speed.getColumnIndex("speed")),
                speed.getString(speed.getColumnIndex("activity")), speed.getInt(speed.getColumnIndex("time")))));
        caloriesMinutes.setText(String.valueOf(caloriesPerMinute) + "/m");
        distance.setText(String.valueOf(speed.getInt(speed.getColumnIndex("km"))) + " km");

        // create pie chart
        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        mPieChart.addPieSlice(new PieModel("Calories Burned", calcCalories(speed.getInt(speed.getColumnIndex("speed")),
                speed.getString(speed.getColumnIndex("activity")), speed.getInt(speed.getColumnIndex("time"))), Color.parseColor("#f4a227")));
        mPieChart.startAnimation();

        speed.close();
    }


    // calculate calories burned based on "METS" & SPEED
    public int calcCalories(int speed, String activity, int time) {
        if (activity.equals("Walking")) {
            if (speed <= 3) {
                METS = 2;
            } else if (speed > 3 && speed <= 4) {
                METS = 3;
            } else if (speed > 4 && speed <= 5.5) {
                METS = 4;
            } else if (speed > 5.5 && speed <= 7) {
                METS = 5;
            } else {
                METS = 6;
            }
        } else if (activity.equals("Running")) {
            if (speed <= 8) {
                METS = 5;
            } else if (speed > 8 && speed <= 9.5) {
                METS = 8;
            } else if (speed > 9.5 && speed <= 11.5) {
                METS = 10;
            } else if (speed > 11.5 && speed <= 13) {
                METS = 12;
            } else if (speed > 13 && speed <= 15) {
                METS = 14;
            } else if (speed > 15 && speed <= 17.5) {
                METS = 16;
            } else {
                METS = 18;
            }
        } else if (activity.equals("Cycling")) {
            if (speed <= 16) {
                METS = 4;
            } else if (speed > 16 && speed <= 19.5) {
                METS = 6;
            } else if (speed > 19.5 && speed <= 22.5) {
                METS = 8;
            } else if (speed > 22.5 && speed <= 25.5) {
                METS = 10;
            } else if (speed > 25.5 && speed <= 29) {
                METS = 12;
            } else {
                METS = 16;
            }
        }

        int caloriesPerHour = METS * 70;
        caloriesPerMinute = caloriesPerHour / 60;
        int totalCaloriesBurned = caloriesPerMinute * time;

        return totalCaloriesBurned;
    }


    // add confetti on congratulations button to celebrate the routine
    public void onConfetti(View view) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.Cardio_celebrate);
        CommonConfetti.rainingConfetti(viewGroup, new int[]{Color.parseColor("#FFA500")})
                .infinite();

        Button confetti = findViewById(R.id.celebrate);
        confetti.setEnabled(false);
    }
}
