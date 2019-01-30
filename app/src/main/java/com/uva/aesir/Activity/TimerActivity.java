/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to allow the user to time their workouts / have a timer to work with.
 **
 ***/

package com.uva.aesir.Activity;

// "inspiration" >> https://www.android-examples.com/android-create-stopwatch-example-tutorial-in-android-studio/

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.uva.aesir.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimerActivity extends AppCompatActivity {
    TextView timer;
    ListView listview;
    Handler handler;
    Button start, pause, reset, lap;
    long millisecondTime, startTime, timeBuffer, updateTime = 0L;
    int seconds, minutes, milliseconds;
    String[] listElements = new String[]{};
    List<String> listElementsArrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timer = (TextView) findViewById(R.id.timer_screen);
        start = (Button) findViewById(R.id.start_button);
        pause = (Button) findViewById(R.id.pause_button);
        reset = (Button) findViewById(R.id.reset_button);
        lap = (Button) findViewById(R.id.lap_button);
        listview = (ListView) findViewById(R.id.lap_list);

        // setup handler, arraylist & adapter
        handler = new Handler();
        listElementsArrayList = new ArrayList<String>(Arrays.asList(listElements));
        adapter = new ArrayAdapter<String>(TimerActivity.this, android.R.layout.simple_list_item_1, listElementsArrayList);
        listview.setAdapter(adapter);

        // setup click listener
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                start.setEnabled(false);
                reset.setEnabled(false);
            }
        });

        // setup click listener
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBuffer += millisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                start.setEnabled(true);
            }
        });

        // setup click listener
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                millisecondTime = 0L;
                startTime = 0L;
                timeBuffer = 0L;
                updateTime = 0L;
                seconds = 0;
                minutes = 0;
                milliseconds = 0;

                timer.setText("00:00:00");

                listElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        // setup click listener > make sure that when a new lap is timed the views scrolls smoothly to the location
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listElementsArrayList.add(0, timer.getText().toString());
                adapter.notifyDataSetChanged();
                listview.smoothScrollToPosition(0);
            }
        });
    }


    // create new runnable to calculate data and update text on screen
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuffer + millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliseconds = (int) (updateTime % 1000);
            timer.setText("" + minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliseconds));

            handler.postDelayed(this, 0);
        }
    };
}
