package com.example.kairo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeeklyStats {

    // Focus hours for each day Mon → Sun
    private final List<Float> focusHours;

    // Break hours for each day (optional)
    private final List<Float> breakHours;

    // Summary values
    private final float totalFocusHours;
    private final int longestStreak;

    public WeeklyStats(List<Float> focusHours, List<Float> breakHours) {
        this.focusHours = focusHours;
        this.breakHours = breakHours;

        // Calculate total automatically
        float total = 0f;
        for (Float f : focusHours) total += f;
        this.totalFocusHours = total;

        // Dummy streak calculation (replace later)
        this.longestStreak = calculateStreak(focusHours);
    }

    // ======== DUMMY STATS FOR TESTING UI ========
    public static WeeklyStats generateDummy() {
        return new WeeklyStats(
                Arrays.asList(2f, 1.5f, 3f, 0.5f, 4f, 2.5f, 1f),
                Arrays.asList(0.5f, 0.3f, 1f, 0.2f, 1f, 0.8f, 0.4f)
        );
    }

    // ======== SIMPLE STREAK LOGIC ========
    private int calculateStreak(List<Float> hours) {
        int streak = 0;
        int max = 0;

        for (Float f : hours) {
            if (f > 0f) {
                streak++;
                max = Math.max(max, streak);
            } else {
                streak = 0;
            }
        }
        return max;
    }

    // ======== GETTER METHODS ========
    public List<Float> getFocusHours() {
        return focusHours;
    }

    public List<Float> getBreakHours() {
        return breakHours;
    }

    public float getTotalFocusHours() {
        return totalFocusHours;
    }

    public int getLongestStreak() {
        return longestStreak;
    }
}
