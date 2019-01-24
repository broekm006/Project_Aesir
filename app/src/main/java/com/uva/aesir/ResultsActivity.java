package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jinatonic.confetti.CommonConfetti;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ResultsActivity extends AppCompatActivity {
    WeightsDatabase db;
    String exerciseName;
    String a, b, c, d, a1, b1, c1, d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();

        exerciseName = intent.getStringExtra("exerciseName");

        db = WeightsDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultsActivity.this, MainActivity.class));
    }

    public void onAttempt(View view) {
        Cursor cs = db.selectResults(exerciseName);
        cs.moveToFirst();
        if (!cs.moveToFirst()) {
            a = "0";
            b = "0";
            c = "0";
            d = "0";
        } else {
            a = cs.getString(cs.getColumnIndex("setA"));
            b = cs.getString(cs.getColumnIndex("setB"));
            c = cs.getString(cs.getColumnIndex("setC"));
            d = cs.getString(cs.getColumnIndex("setD"));
        }
        GraphView graphView = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, Integer.parseInt(a)),
                new DataPoint(2, Integer.parseInt(b)),
                new DataPoint(3, Integer.parseInt(c)),
                new DataPoint(4, Integer.parseInt(d))
        });

        series.setColor(Color.parseColor("#FF0000"));
        series.setThickness(8);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(40);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1);
        graphView.getViewport().setMaxX(4);

        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.addSeries(series);


        Cursor cs1 = db.selectResults(exerciseName);
        cs1.moveToFirst();
        if (!cs1.moveToNext()) {
            a1 = "0";
            b1 = "0";
            c1 = "0";
            d1 = "0";
        } else {
            //cs1.moveToNext();
            a1 = cs1.getString(cs1.getColumnIndex("setA"));
            b1 = cs1.getString(cs1.getColumnIndex("setB"));
            c1 = cs1.getString(cs1.getColumnIndex("setC"));
            d1 = cs1.getString(cs1.getColumnIndex("setD"));
        }

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, Integer.parseInt(a1)),
                new DataPoint(2, Integer.parseInt(b1)),
                new DataPoint(3, Integer.parseInt(c1)),
                new DataPoint(4, Integer.parseInt(d1))
        });

        series2.setColor(Color.parseColor("#0000FF"));
        series.setThickness(8);
        graphView.addSeries(series2);


        // confetti
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.yesman);
        CommonConfetti.rainingConfetti(viewGroup, new int[]{Color.BLUE})
                .infinite();


        // barchart
        GraphView graph = (GraphView) findViewById(R.id.bargraph);
        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0,-2),
                new DataPoint(1, 3),
                new DataPoint(2,5),
                new DataPoint(3,1),
                new DataPoint(4,2)
        });
        graph.addSeries(series1);

        // barchart styling
        series1.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series1.setSpacing(5);

        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(Color.RED);
    }
}
