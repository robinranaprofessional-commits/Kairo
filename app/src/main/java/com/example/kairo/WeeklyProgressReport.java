package com.example.kairo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class WeeklyProgressReport extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;
    TextView summaryText;

    WeeklyStats stats;   // model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_progress_report);

        barChart = findViewById(R.id.bar_chart);
        pieChart = findViewById(R.id.pie_chart);
        summaryText = findViewById(R.id.tv_summary);

        // Get dummy data for now
        stats = WeeklyStats.generateDummy();

        setupBarChart();
        setupPieChart();
        setupSummary();
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        List<Float> hours = stats.getFocusHours();

        for (int i = 0; i < hours.size(); i++) {
            entries.add(new BarEntry(i, hours.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Focus Hours");
        dataSet.setColor(getResources().getColor(R.color.clayCream));

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.5f);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.invalidate();
    }

    private void setupPieChart() {
        float totalFocus = stats.getTotalFocusHours();
        float totalBreak = 0;
        for (Float b : stats.getBreakHours()) totalBreak += b;

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(totalFocus, "Focus Time"));
        entries.add(new PieEntry(totalBreak, "Break Time"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(
                getResources().getColor(R.color.clayCream),
                getResources().getColor(R.color.skinCream)
        );

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(55f);

        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();
    }

    private void setupSummary() {
        summaryText.setText(
                "Total Focus Hours: " + stats.getTotalFocusHours() + "h\n" +
                        "Longest Streak: " + stats.getLongestStreak() + " days"
        );
    }
}
