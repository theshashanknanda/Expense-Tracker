package com.example.expensetracker.Activities.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Activities.Activities.AddExpense;
import com.example.expensetracker.Activities.Adapters.RecylerViewAdapter;
import com.example.expensetracker.R;

import java.util.ArrayList;
import java.util.List;

import Room.ExpenseEntity;
import Room.ExpenseViewModel;

public class ExpenseFragment extends Fragment {
    public Button button;
    public TextView amountTextView;
    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expenses_fragment, container, false);

        button = view.findViewById(R.id.button_addExpense);
        amountTextView = view.findViewById(R.id.amountTextView);
        recyclerView = view.findViewById(R.id.recyclerView);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExpense.class);
            startActivity(intent);
        });

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        amountTextView.setText(sharedPreferences.getString("totalExpense", "0"));

        ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        List<ExpenseEntity> expenseList = new ArrayList<>();
        expenseList = viewModel.getItems;
        int totalExpense = 0;
        for(ExpenseEntity model: expenseList)
        {
            int currentExpense = model.amount;
            totalExpense = totalExpense + currentExpense;
        }
        amountTextView.setText("$"+String.valueOf(totalExpense));

        RecylerViewAdapter adapter = new RecylerViewAdapter(getActivity(), expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);

        return view;
    }
}

