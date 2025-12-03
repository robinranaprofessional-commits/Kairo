package com.example.kairo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// this activity shows the list of habits
public class MyHabitsActivity extends AppCompatActivity
        implements HabitAdapter.OnDeleteClickListener {

    private RecyclerView rvHabits;
    private Button btnAddHabit;

    private ArrayList<Habit> habitList;
    private HabitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        // connect the views from xml
        rvHabits = findViewById(R.id.rvHabits);
        btnAddHabit = findViewById(R.id.btnAddHabit);

        // starting habits so the list isn't empty
        habitList = new ArrayList<>();
        habitList.add(new Habit("Habit 1", false));
        habitList.add(new Habit("Habit 2", false));
        habitList.add(new Habit("New Habit Placeholder", true));
        habitList.add(new Habit("New Habit Placeholder", true));

        // set up the recycler view
        adapter = new HabitAdapter(habitList, this);
        rvHabits.setLayoutManager(new LinearLayoutManager(this));
        rvHabits.setAdapter(adapter);

        // add a new placeholder when the button is clicked
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitList.add(new Habit("New Habit Placeholder", true));
                int newIndex = habitList.size() - 1;
                adapter.notifyItemInserted(newIndex);
                rvHabits.scrollToPosition(newIndex);
            }
        });
    }

    // called when delete is pressed on a row
    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < habitList.size()) {
            habitList.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }
}