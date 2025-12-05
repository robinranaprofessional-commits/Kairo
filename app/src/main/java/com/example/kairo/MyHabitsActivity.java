package com.example.kairo;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

public class MyHabitsActivity extends AppCompatActivity
        implements HabitAdapter.OnDeleteClickListener, HabitAdapter.OnItemClickListener {

    private static final String PREFS = "kairo_prefs";
    private static final String KEY_HABITS = "habits_json";

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

        habitList = loadHabitsFromPrefs();

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
        input.setHint("e.g. Drink water");
        input.setSingleLine(true);

        if (isEdit) {
            input.setText(habitList.get(position).getName());
            input.setSelection(input.getText().length());
        }

        int padding = (int) (16 * getResources().getDisplayMetrics().density);
        input.setPadding(padding, padding/2, padding, padding/2);
        builder.setView(input);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton(isEdit ? "Save" : "Add", (dialog, which) -> {});

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String text = input.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                input.setError("Please enter a habit name");
                return;
            }

            if (isEdit) {
                Habit h = habitList.get(position);
                h.setName(text);
                h.setPlaceholder(false);
                adapter.updateItem(position, h);
                Toast.makeText(MyHabitsActivity.this, "Habit updated", Toast.LENGTH_SHORT).show();
            } else {
                Habit newHabit = new Habit(text, false);
                adapter.addItem(newHabit);
                rvHabits.scrollToPosition(habitList.size() - 1);
                Toast.makeText(MyHabitsActivity.this, "Habit added", Toast.LENGTH_SHORT).show();
            }

            saveHabitsToPrefs(habitList);
            dialog.dismiss();
        });
    }

    @Override
    public void onDeleteClick(final int position) {
        if (position >= 0 && position < habitList.size()) {
            final Habit toDelete = habitList.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Delete habit")
                    .setMessage("Delete \"" + toDelete.getName() + "\"?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        adapter.removeItem(position);
                        saveHabitsToPrefs(habitList);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < habitList.size()) {
            showAddEditDialog(position);
        }
    }

    // save list of names as JSON array
    private void saveHabitsToPrefs(ArrayList<Habit> list) {
        JSONArray arr = new JSONArray();
        for (Habit h : list) arr.put(h.getName());
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        prefs.edit().putString(KEY_HABITS, arr.toString()).apply();
    }

    private ArrayList<Habit> loadHabitsFromPrefs() {
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String json = prefs.getString(KEY_HABITS, null);
        ArrayList<Habit> out = new ArrayList<>();
        if (json == null) {
            out.add(new Habit("Drink Water", false));
            out.add(new Habit("Read for 15 Minutes", false));
            out.add(new Habit("Morning Stretch", false));
            out.add(new Habit("Practice Coding", false));
            return out;
        }
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                String name = arr.optString(i, "").trim();
                if (!name.isEmpty()) out.add(new Habit(name, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}