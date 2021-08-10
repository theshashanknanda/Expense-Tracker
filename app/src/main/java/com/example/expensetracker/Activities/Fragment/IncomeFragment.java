package com.example.expensetracker.Activities.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.expensetracker.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class IncomeFragment extends BottomSheetDialogFragment {

    public EditText incomeEditText;
    public Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.income_bottom_sheet, container, false);

        incomeEditText = view.findViewById(R.id.incomeEditText);
        button = view.findViewById(R.id.doneButton);

        button.setOnClickListener(v -> {
            String income = incomeEditText.getText().toString();

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("incomePref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("incomeKey", income);
            editor.apply();

            dismiss();
            getActivity().recreate();
        });

        return view;
    }
}
