package com.uva.aesir;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

public class ResultsCardioActivity extends AppCompatActivity {
    int METS, CaloriesPerMinute = 0;
    CardioDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_cardio);

        db = CardioDatabase.getInstance(getApplicationContext());
        Cursor speed = db.selectAll();
        speed.moveToFirst();

        TextView activity = findViewById(R.id.activity_title);
        TextView calories = findViewById(R.id.Calories_result);
        TextView caloriesMinutes = findViewById(R.id.textView7);
        TextView distance = findViewById(R.id.Distance_result);
        TextView maxDistance = findViewById(R.id.textView8);


        activity.setText(String.valueOf(speed.getString(speed.getColumnIndex("activity"))));
        calories.setText(String.valueOf(calcCalories(speed.getInt(speed.getColumnIndex("speed")), speed.getString(speed.getColumnIndex("activity")), speed.getInt(speed.getColumnIndex("time")))));
        caloriesMinutes.setText(String.valueOf(CaloriesPerMinute));
        distance.setText(String.valueOf(speed.getInt(speed.getColumnIndex("km"))));
        maxDistance.setText(String.valueOf(speed.getInt(speed.getColumnIndex("km"))));




        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        mPieChart.addPieSlice(new PieModel("Calories Burned", calcCalories(speed.getInt(speed.getColumnIndex("speed")), speed.getString(speed.getColumnIndex("activity")), speed.getInt(speed.getColumnIndex("time"))), Color.parseColor("#CDA67F")));
        mPieChart.startAnimation();

        speed.close();

    }

    public int calcCalories(int speed, String activity, int time) {
        System.out.println(speed);
        System.out.println(activity);
        System.out.println(time);
        int distance = 0;

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

        int CaloriesPerHour = METS * 70;
        System.out.println(CaloriesPerHour);
        CaloriesPerMinute = CaloriesPerHour / 60;
        System.out.println(CaloriesPerMinute);
        int TotalCaloriesBurned = CaloriesPerMinute * time;
        System.out.println(TotalCaloriesBurned);

        return TotalCaloriesBurned;
    }


}
