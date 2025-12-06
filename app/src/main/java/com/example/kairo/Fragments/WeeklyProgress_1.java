package com.example.kairo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kairo.IntroScreen;
import com.example.kairo.R;
import com.example.kairo.WeeklyStats;
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

public class WeeklyProgress_1 extends Fragment {

    BarChart barChart;
    PieChart pieChart;
    TextView summaryText;
    Button btnLogout;

    WeeklyStats stats;  // model

    public WeeklyProgress_1() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_weekly_progress_report, container, false);

        // Find views from fragment layout
        barChart = view.findViewById(R.id.bar_chart);
        pieChart = view.findViewById(R.id.pie_chart);
        summaryText = view.findViewById(R.id.tv_summary);

        // Get dummy or real data
        stats = WeeklyStats.generateDummy();

        setupBarChart();
        setupPieChart();
        setupSummary();

        return view;
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        List<Float> hours = stats.getFocusHours();

        for (int i = 0; i < hours.size(); i++) {
            entries.add(new BarEntry(i, hours.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Focus Hours");
        dataSet.setColor(requireContext().getColor(R.color.clayCream));

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
                requireContext().getColor(R.color.clayCream),
                requireContext().getColor(R.color.skinCream)
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

