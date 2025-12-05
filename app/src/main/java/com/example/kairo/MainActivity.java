package com.example.kairo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Set adapter
        KairoPagerAdapter adapter = new KairoPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Attach tabs
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Daily");
                    tab.setIcon(R.drawable.dailyfocusicon);
                    break;
                case 1:
                    tab.setText("Weekly");
                    tab.setIcon(R.drawable.weeklyanalysisicon);
                    break;
                case 2:
                    tab.setText("Focus");
                    tab.setIcon(R.drawable.focustimericon);
                    break;
            }
        }).attach();

        // DEFAULT TAB = DailyProgress
        viewPager.setCurrentItem(0, false);

    }
    public void goToPage(int pageIndex) {
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setCurrentItem(pageIndex, true);
    }

}
