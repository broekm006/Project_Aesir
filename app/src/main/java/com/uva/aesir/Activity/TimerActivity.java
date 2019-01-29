package com.uva.aesir.Activity;

// "inspiratie" >> https://www.android-examples.com/android-create-stopwatch-example-tutorial-in-android-studio/

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
    long MillisecondTime, StartTime, TimeBuffer, UpdateTime = 0L;
    int Seconds, Minutes, Milliseconds;
    String[] ListElements = new String[]{};
    List<String> ListElementsArrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timer = (TextView) findViewById(R.id.Timer);
        start = (Button) findViewById(R.id.Start);
        pause = (Button) findViewById(R.id.Pause);
        reset = (Button) findViewById(R.id.Reset);
        lap = (Button) findViewById(R.id.Lap);
        listview = (ListView) findViewById(R.id.lap_list);

        // setup handler, arraylist & adapter
        handler = new Handler();
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
        adapter = new ArrayAdapter<String>(TimerActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);

        // setup click listener
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                start.setEnabled(false);
                reset.setEnabled(false);
            }
        });

        // setup click listener
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuffer += MillisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                start.setEnabled(true);
            }
        });

        // setup click listener
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuffer = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                Milliseconds = 0;

                timer.setText("00:00:00");

                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        // setup click listener > make sure that when a new lap is timed the views scrolls smoothly to the location
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListElementsArrayList.add(0, timer.getText().toString());
                adapter.notifyDataSetChanged();
                listview.smoothScrollToPosition(0);
            }
        });
    }


    // create new runnable to calculate data and update text on screen
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuffer + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            Milliseconds = (int) (UpdateTime % 1000);
            timer.setText("" + Minutes + ":" + String.format("%02d", Seconds) + ":" + String.format("%03d", Milliseconds));

            handler.postDelayed(this, 0);
        }
    };
}
