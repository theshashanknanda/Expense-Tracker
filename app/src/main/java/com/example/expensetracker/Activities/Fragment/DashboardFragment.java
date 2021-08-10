package com.example.expensetracker.Activities.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensetracker.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import Room.ExpenseEntity;
import Room.ExpenseViewModel;

public class DashboardFragment extends Fragment {
    public TextView incomeTextView;
    public TextView budgetTextView;
    public TextView expenseTextView;
    public ProgressBar progressBar;
    public PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        incomeTextView = view.findViewById(R.id.incomeTextView_id);
        expenseTextView = view.findViewById(R.id.expenseTextView_dashboard);
        budgetTextView = view.findViewById(R.id.budgetTextView_id);
        progressBar = view.findViewById(R.id.progressBar);
        pieChart = view.findViewById(R.id.piechart);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("incomePref", Context.MODE_PRIVATE);
        String income = sharedPreferences.getString("incomeKey", "1");

        incomeTextView.setText("+"+income);

        incomeTextView.setOnClickListener(v -> {
            // Bottom Sheet
            IncomeFragment bottomSheet = new IncomeFragment();
            bottomSheet.show(getActivity().getSupportFragmentManager(), "bottomSheetFragmentTag");
        });

        ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        List<ExpenseEntity> expenseList = new ArrayList<>();
        expenseList = viewModel.getItems;
        int totalExpense = 0;
        int home = 0, work = 0, personal = 0;
        for(ExpenseEntity model: expenseList)
        {
            int currentExpense = model.amount;
            totalExpense = totalExpense + currentExpense;

            if(model.category.equals("Home")){
                home++;
            }
            else if(model.category.equals("Work")){
                work++;
            }
            else if(model.category.equals("Personal")){
                personal++;
            }
        }
        expenseTextView.setText("-$"+String.valueOf(totalExpense));
        budgetTextView.setText("$"+String.valueOf(income));

        int progress = (100 * totalExpense) / Integer.parseInt(income);
        progressBar.setMax(100);

        if(progress >= 0){
            progressBar.setProgress(progress);
        }
        else{
            progressBar.setProgress(0);
        }

        pieChart.addPieSlice(new PieModel("home", home, Color.parseColor("#185ADB")));
        pieChart.addPieSlice(new PieModel("work", work, Color.parseColor("#11FF7F")));
        pieChart.addPieSlice(new PieModel("personal", personal, Color.parseColor("#FFEC40")));

        return view;
    }
}
