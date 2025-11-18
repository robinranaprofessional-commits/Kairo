package com.example.kairo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DailyProgress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_progress);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


/*
HOW TO USE THIS LAYOUT IN JAVA (REFERENCE)

1. CLASS SETUP
--------------------------------------------------
public class MainActivity extends AppCompatActivity {

    // 1.1 Declare views at the top of the class
    private TextView tvGreeting;
    private TextView tvSubtitle;

    private ImageView star1;
    private ImageView star2;
    private ImageView star3;

    private TextView habitText1;
    private TextView habitText2;
    private TextView habitText3;

    private View avatarButton;      // FrameLayout or ImageView
    private Button btnViewStats;
    private Button btnStartFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // make sure this matches your XML filename

        // 1.2 Connect views to XML ids
        tvGreeting = findViewById(R.id.tvGreeting);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);

        habitText1 = findViewById(R.id.habitText1);
        habitText2 = findViewById(R.id.habitText2);
        habitText3 = findViewById(R.id.habitText3);

        avatarButton = findViewById(R.id.avatarButton);
        btnViewStats = findViewById(R.id.btnViewStats);
        btnStartFocus = findViewById(R.id.btnStartFocus);

        // 1.3 Example: set greeting text dynamically
        tvGreeting.setText("Good Morning"); // later you can use the user's name here

        // 1.4 Example: set subtitle text
        tvSubtitle.setText("Here's your progress today");

        // 1.5 Example: set example user progress + stars
        // Imagine you calculate these from your logic later:
        int habit1Percent = 60;
        int habit2Percent = 30;
        int habit3Percent = 90;
        int starCount = 2; // 0, 1, 2, or 3

        updateHabitTexts(habit1Percent, habit2Percent, habit3Percent);
        updateStars(starCount);

        // 1.6 Set up button click listeners
        setupClickListeners();
    }

    // 2. UPDATE STARS BASED ON USER PROGRESS
    // --------------------------------------------------
    // count can be 0, 1, 2, or 3
    private void updateStars(int count) {
        int filled = android.R.drawable.btn_star_big_on;
        int empty = android.R.drawable.btn_star_big_off;

        star1.setImageResource(count >= 1 ? filled : empty);
        star2.setImageResource(count >= 2 ? filled : empty);
        star3.setImageResource(count >= 3 ? filled : empty);
    }

    // 3. UPDATE HABIT PERCENTAGES
    // --------------------------------------------------
    // Call this whenever habit progress changes
    private void updateHabitTexts(int habit1Percent, int habit2Percent, int habit3Percent) {
        habitText1.setText("Habit 1: " + habit1Percent + "%");
        habitText2.setText("Habit 2: " + habit2Percent + "%");
        habitText3.setText("Habit 3: " + habit3Percent + "%");
    }

    // 4. SET UP BUTTON CLICKS
    // --------------------------------------------------
    private void setupClickListeners() {
        // 4.1 Profile button (avatarButton)
        avatarButton.setOnClickListener(v -> {
            // TODO: open profile screen or settings later
            Toast.makeText(this, "Profile button clicked", Toast.LENGTH_SHORT).show();
        });

        // 4.2 View Stats button
        btnViewStats.setOnClickListener(v -> {
            // TODO: open a stats page / activity
            Toast.makeText(this, "View Stats clicked", Toast.LENGTH_SHORT).show();
        });

        // 4.3 Start Focus Session button
        btnStartFocus.setOnClickListener(v -> {
            // TODO: start a timer, open focus mode screen, etc.
            Toast.makeText(this, "Focus session started", Toast.LENGTH_SHORT).show();
        });
    }

    // 5. EXAMPLE: HOW YOU MIGHT USE REAL DATA LATER
    // --------------------------------------------------
    // Imagine later you have user data like:
    //
    // int totalMinutesGoal = 60;
    // int minutesDone = 30;
    // int progressPercent = (minutesDone * 100) / totalMinutesGoal;  // 50%
    //
    // You could:
    //
    // updateHabitTexts(progressPercent, 0, 0);
    // updateStars(calculateStarCountFromPercent(progressPercent));
    //
    // And define:
    //
    // private int calculateStarCountFromPercent(int percent) {
    //     if (percent >= 80) return 3;
    //     if (percent >= 50) return 2;
    //     if (percent >= 20) return 1;
    //     return 0;
    // }
}
*/