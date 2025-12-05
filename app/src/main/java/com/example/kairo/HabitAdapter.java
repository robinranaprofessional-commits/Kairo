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

    private final ArrayList<Habit> items;
    private final OnDeleteClickListener deleteListener;
    private final OnItemClickListener itemClickListener;

    public HabitAdapter(ArrayList<Habit> items,
                        OnDeleteClickListener deleteListener,
                        OnItemClickListener itemClickListener) {
        this.items = items;
        this.deleteListener = deleteListener;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = items.get(position);

        if (habit.isPlaceholder()) {
            holder.tvName.setText("New Habit Placeholder");
            holder.tvStreak.setText("");
        } else {
            holder.tvName.setText(habit.getName());
            holder.tvStreak.setText("Streak Count:");
        }

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                deleteListener.onDeleteClick(pos);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                itemClickListener.onItemClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void addItem(Habit habit) {
        items.add(habit);
        notifyItemInserted(items.size() - 1);
    }

    public void updateItem(int position, Habit habit) {
        items.set(position, habit);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

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
}