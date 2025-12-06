package com.example.kairo;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

// this activity shows the list of habits
public class MyHabitsActivity extends Activity
        implements HabitAdapter.OnDeleteClickListener, HabitAdapter.OnItemClickListener {

    private RecyclerView rvHabits;
    private Button btnAddHabit;

    private ArrayList<Habit> habitList;
    private HabitAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        if(checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

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
        notificationChannel();
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

    private void scheduleHabitReminder(String habitName, int hour, int minute, int position)
    {
        Intent intent = new Intent(this, HabitReminderReceiver.class);
        intent.putExtra("habit_name", habitName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, position, intent, PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance()))
        {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    private void notificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("kairo_reminders", "Kairo Habit Reminders", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Habit Notifications");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
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
    public void onItemClick(int position)
    {
        Habit habit = habitList.get(position);

        Calendar current = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(this, (view, hour, minute) ->
        {
            scheduleHabitReminder(habit.getName(), hour, minute, position);
            Toast.makeText(this, "Reminder set for " + habit.getName(), Toast.LENGTH_SHORT).show();
        }, current.get(Calendar.HOUR_OF_DAY), current.get(Calendar.MINUTE), true);
        
        showAddEditDialog(position);
        dialog.show();
    }
}