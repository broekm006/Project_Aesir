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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ResultsActivity extends AppCompatActivity {
    WeightsDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();

        intent.getStringExtra("exerciseName");

        db = WeightsDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultsActivity.this, MainActivity.class));
    }

    public void onAttempt(View view){
        Cursor cs = db.selectResults("Arnold Press");
        cs.moveToFirst();
        String a = cs.getString(cs.getColumnIndex("setA"));
        String b = cs.getString(cs.getColumnIndex("setB"));
        String c = cs.getString(cs.getColumnIndex("setC"));
        String d = cs.getString(cs.getColumnIndex("setD"));

        GraphView graphView = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
            new DataPoint(0, Integer.parseInt(a)),
            new DataPoint(1, Integer.parseInt(b)),
            new DataPoint(2, Integer.parseInt(c)),
            new DataPoint(3, Integer.parseInt(d))
        });

        series.setColor(Color.parseColor("#FF0000"));
        series.setThickness(8);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(50);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(4);

        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.addSeries(series);


        Cursor cs1 = db.selectResults("Arnold Press");
        cs1.moveToFirst();
        cs1.moveToNext();

        String a1 = cs1.getString(cs1.getColumnIndex("setA"));
        String b1 = cs1.getString(cs1.getColumnIndex("setB"));
        String c1 = cs1.getString(cs1.getColumnIndex("setC"));
        String d1 = cs1.getString(cs1.getColumnIndex("setD"));

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, Integer.parseInt(a1)),
                new DataPoint(1, Integer.parseInt(b1)),
                new DataPoint(2, Integer.parseInt(c1)),
                new DataPoint(3, Integer.parseInt(d1))
        });

        series2.setColor(Color.parseColor("#0000FF"));
        series.setThickness(8);
        graphView.addSeries(series2);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.yesman);
        CommonConfetti.rainingConfetti(viewGroup, new int[] { Color.BLUE })
                .infinite();
    }
}
