package com.example.kairo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// adapter for showing all the habits in the list
public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    // lets the activity know when delete is pressed
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    private ArrayList<Habit> items;
    private OnDeleteClickListener deleteListener;

    public HabitAdapter(ArrayList<Habit> items, OnDeleteClickListener deleteListener) {
        this.items = items;
        this.deleteListener = deleteListener;
    }

    // holds the views for one row
    public static class HabitViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvStreak;
        ImageButton btnDelete;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvHabitName);
            tvStreak = itemView.findViewById(R.id.tvStreakLabel);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // make one row from the xml layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = items.get(position);

        // show placeholder text or the real habit
        if (habit.isPlaceholder()) {
            holder.tvName.setText("New Habit Placeholder");
            holder.tvStreak.setText("");
        } else {
            holder.tvName.setText(habit.getName());
            holder.tvStreak.setText("Streak Count:");
        }

        // handle delete button for this row
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteListener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteClick(pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}