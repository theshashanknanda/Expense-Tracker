package com.example.expensetracker.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.expensetracker.Activities.Adapters.FragmentPageAdapter;
import com.example.expensetracker.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
