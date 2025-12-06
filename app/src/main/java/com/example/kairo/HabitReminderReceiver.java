package com.example.kairo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class HabitReminderReceiver extends BroadcastReceiver {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    public void onReceive(Context context, Intent intent)
    {
        String habitName = intent.getStringExtra("habit_name");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "kairo_reminders").setSmallIcon(R.drawable.kairo).setContentTitle("Kairo Reminder").setContentText("It's time for: " + habitName).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);

        NotificationManagerCompat.from(context).notify((int) System.currentTimeMillis(), builder.build());
    }
}
