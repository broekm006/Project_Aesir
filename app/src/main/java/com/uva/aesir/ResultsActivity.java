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
import android.widget.Button;
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
    String getSetA, getSetB, getSetC, getSetD, getSetA1, getSetB1, getSetC1, getSetD1, getSetA2,
            getSetB2, getSetC2, getSetD2, getSetA3, getSetB3, getSetC3, getSetD3, getSetA4,
            getSetB4, getSetC4, getSetD4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();

        exerciseName = intent.getStringExtra("exerciseName");

        db = WeightsDatabase.getInstance(getApplicationContext());
        generateGraph();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultsActivity.this, MainActivity.class));
    }

    public void onAttempt(View view) {
        // add confetti on congratulations button to celebrate the routine
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.confetti);
        CommonConfetti.rainingConfetti(viewGroup, new int[]{Color.BLUE})
                .infinite();

        Button confetti = findViewById(R.id.button5);
        confetti.setEnabled(false);
    }

    public void generateGraph() {
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

        cs.close();
        cs1.close();


        // barchart
        Cursor bar = db.selectResults(exerciseName);
        bar.moveToFirst();

        if (!bar.moveToFirst()) {
            getSetA = "0";
            getSetB = "0";
            getSetC = "0";
            getSetD = "0";
        } else {
            getSetA = bar.getString(bar.getColumnIndex("setA"));
            getSetB = bar.getString(bar.getColumnIndex("setB"));
            getSetC = bar.getString(bar.getColumnIndex("setC"));
            getSetD = bar.getString(bar.getColumnIndex("setD"));
        }

        int totalWeights = Integer.parseInt(getSetA) + Integer.parseInt(getSetB) + Integer.parseInt(getSetC) + Integer.parseInt(getSetD);

        if (!bar.moveToNext()) {
            getSetA1 = "0";
            getSetB1 = "0";
            getSetC1 = "0";
            getSetD1 = "0";
        } else {
            getSetA1 = bar.getString(bar.getColumnIndex("setA"));
            getSetB1 = bar.getString(bar.getColumnIndex("setB"));
            getSetC1 = bar.getString(bar.getColumnIndex("setC"));
            getSetD1 = bar.getString(bar.getColumnIndex("setD"));
        }

        int totalWeightsMin1 = Integer.parseInt(getSetA1) + Integer.parseInt(getSetB1) + Integer.parseInt(getSetC1) + Integer.parseInt(getSetD1);

        if (!bar.moveToNext()) {
            getSetA2 = "0";
            getSetB2 = "0";
            getSetC2 = "0";
            getSetD2 = "0";
        } else {
            getSetA2 = bar.getString(bar.getColumnIndex("setA"));
            getSetB2 = bar.getString(bar.getColumnIndex("setB"));
            getSetC2 = bar.getString(bar.getColumnIndex("setC"));
            getSetD2 = bar.getString(bar.getColumnIndex("setD"));
        }

        int totalWeightsMin2 = Integer.parseInt(getSetA2) + Integer.parseInt(getSetB2) + Integer.parseInt(getSetC2) + Integer.parseInt(getSetD2);

        if (!bar.moveToNext()) {
            getSetA3 = "0";
            getSetB3 = "0";
            getSetC3 = "0";
            getSetD3 = "0";
        } else {
            getSetA3 = bar.getString(bar.getColumnIndex("setA"));
            getSetB3 = bar.getString(bar.getColumnIndex("setB"));
            getSetC3 = bar.getString(bar.getColumnIndex("setC"));
            getSetD3 = bar.getString(bar.getColumnIndex("setD"));
        }

        int totalWeightsMin3 = Integer.parseInt(getSetA3) + Integer.parseInt(getSetB3) + Integer.parseInt(getSetC3) + Integer.parseInt(getSetD3);

        if (!bar.moveToNext()) {
            getSetA4 = "0";
            getSetB4 = "0";
            getSetC4 = "0";
            getSetD4 = "0";
        } else {
            getSetA4 = bar.getString(bar.getColumnIndex("setA"));
            getSetB4 = bar.getString(bar.getColumnIndex("setB"));
            getSetC4 = bar.getString(bar.getColumnIndex("setC"));
            getSetD4 = bar.getString(bar.getColumnIndex("setD"));
        }

        int totalWeightsMin4 = Integer.parseInt(getSetA4) + Integer.parseInt(getSetB4) + Integer.parseInt(getSetC4) + Integer.parseInt(getSetD4);

        GraphView graph = (GraphView) findViewById(R.id.bargraph);
        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, totalWeightsMin4),
                new DataPoint(1, totalWeightsMin3),
                new DataPoint(2, totalWeightsMin2),
                new DataPoint(3, totalWeightsMin1),
                new DataPoint(4, totalWeights)
        });
        graph.addSeries(series1);

        // barchart styling
        series1.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });

        series1.setSpacing(5);

        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(Color.RED);

        bar.close();
    }
}
