package com.example.kairo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// this activity shows the list of habits
public class MyHabitsActivity extends Activity
        implements HabitAdapter.OnDeleteClickListener, HabitAdapter.OnItemClickListener {

    private RecyclerView rvHabits;
    private Button btnAddHabit;

    private ArrayList<Habit> habitList;
    private HabitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        rvHabits = findViewById(R.id.rvHabits);
        btnAddHabit = findViewById(R.id.btnAddHabit);

        habitList = new ArrayList<>();
        habitList.add(new Habit("Drink Water", false));
        habitList.add(new Habit("Read for 15 Minutes", false));
        habitList.add(new Habit("Morning Stretch", false));
        habitList.add(new Habit("Practice Coding", false));

        adapter = new HabitAdapter(habitList, this, this);
        rvHabits.setLayoutManager(new LinearLayoutManager(this));
        rvHabits.setAdapter(adapter);

        btnAddHabit.setOnClickListener(v -> showAddEditDialog(-1));
    }

    private void showAddEditDialog(final int position) {
        final boolean isEdit = position >= 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(isEdit ? "Edit Habit" : "Add New Habit");

        final EditText input = new EditText(this);
        input.setHint("Enter habit name");
        input.setSingleLine(true);

        if (isEdit) {
            input.setText(habitList.get(position).getName());
        }

        builder.setView(input);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton(isEdit ? "Save" : "Add", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String text = input.getText().toString().trim();

            if (TextUtils.isEmpty(text)) {
                input.setError("Please enter a habit");
                return;
            }

            if (isEdit) {
                Habit h = habitList.get(position);
                h.setName(text);
                h.setPlaceholder(false);
                adapter.updateItem(position, h);
                Toast.makeText(this, "Habit updated", Toast.LENGTH_SHORT).show();
            } else {
                adapter.addItem(new Habit(text, false));
                rvHabits.scrollToPosition(habitList.size() - 1);
                Toast.makeText(this, "Habit added", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });
    }

    @Override
    public void onDeleteClick(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Habit")
                .setMessage("Delete this habit?")
                .setPositiveButton("Delete", (dialog, which) -> adapter.removeItem(position))
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onItemClick(int position) {
        showAddEditDialog(position);
    }
}