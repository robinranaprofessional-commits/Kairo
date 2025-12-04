package com.example.kairo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    break;
                case 1:
                    tab.setText("Weekly");
                    break;
                case 2:
                    tab.setText("Focus");
                    break;
            }
        }).attach();

        // DEFAULT TAB = DailyProgress
        viewPager.setCurrentItem(0, false);
    }
}
