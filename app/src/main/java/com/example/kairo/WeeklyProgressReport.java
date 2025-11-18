package com.example.kairo;

import android.os.Bundle;

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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class WeeklyProgressReport extends AppCompatActivity {
    //Initialize variable
    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weekly_progress_report);
        //Assign variable
        barChart = findViewById(R.id.bar_chart);
        pieChart = findViewById(R.id.pie_chart);

        //Initializing Array list, this is a dummy dataset
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //Use for loop
        for(int i =1; i<10; i++)
        {
            float value  = (float) (i*10);
            //initalize bar entry
            BarEntry barEntry = new BarEntry(i, value);
            //initialize pie entry
            PieEntry pieEntry = new PieEntry(value);

            //Add to array list
            barEntries.add(barEntry);
            pieEntries.add(pieEntry);
        }

        //Intialize bar data set
        BarDataSet barDataSet = new BarDataSet(barEntries, "Day of Week");
        //Set Colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Hiding draw values
        barDataSet.setDrawValues(false);
        //set bar data set
        barChart.setData(new BarData(barDataSet));
        //ANIMATion
        barChart.animateY(5000);
        //set Description text and color
        barChart.getDescription().setText("Day of Week");
        barChart.getDescription().setTextColor(R.color.darkCream);


        //Initialize pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Pie Chart");
        //set colors
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //set pie data set
        pieChart.setData(new PieData(pieDataSet));
        //animation
        pieChart.animateXY(5000, 5000);
        //hide description
        pieChart.getDescription().setEnabled(false);






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}