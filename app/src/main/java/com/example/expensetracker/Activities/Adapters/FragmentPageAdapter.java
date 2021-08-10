package com.example.expensetracker.Activities.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.expensetracker.Activities.Fragment.DashboardFragment;
import com.example.expensetracker.Activities.Fragment.ExpenseFragment;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    public String[] titles = new String[]{"Dashboard", "Expenses"};

    public FragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public androidx.fragment.app.Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position)
        {
            case 0:
                fragment = new DashboardFragment();
                break;

            case 1:
                fragment = new ExpenseFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}


