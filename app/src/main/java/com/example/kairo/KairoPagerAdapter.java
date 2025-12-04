package com.example.kairo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kairo.Fragments.DailyProgress_1;
import com.example.kairo.Fragments.FocusTimer_1;
import com.example.kairo.Fragments.WeeklyProgress_1;

public class KairoPagerAdapter extends FragmentStateAdapter {

    public KairoPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DailyProgress_1();
            case 1:
                return new WeeklyProgress_1();
            case 2:
                return new FocusTimer_1();
            default:
                return new DailyProgress_1();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // number of tabs
    }
}

