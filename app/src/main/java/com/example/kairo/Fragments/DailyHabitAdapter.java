package com.example.kairo.Fragments.;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kairo.Habit;

import java.util.List;

public class DailyHabitAdapter extends RecyclerView.Adapter<DailyHabitAdapter.ViewHolder> {

    private final List<Habit> items;

    public DailyHabitAdapter(List<Habit> items) { this.items = items; }

    @NonNull
    @Override
    public DailyHabitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyHabitAdapter.ViewHolder holder, int position) {
        Habit h = items.get(position);
        holder.tvName.setText(h.getName());
        holder.btnDelete.setVisibility(View.GONE);
        holder.tvStreak.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvStreak;
        View btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvHabitName);
            tvStreak = itemView.findViewById(R.id.tvStreakLabel);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}