package com.example.expensetracker.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expensetracker.R;

import Room.ExpenseEntity;
import Room.ExpenseViewModel;

public class UpdateExpense extends AppCompatActivity {

    public Spinner spinner;
    public ImageView deleteButton;
    public Button createItem;
    public EditText itemNameEditText;
    public EditText paymentTypeEditText;
    public EditText amountEditText;
    public String category = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);
        spinner = findViewById(R.id.spinner_up);
        deleteButton = findViewById(R.id.deleteButton_up);
        createItem = findViewById(R.id.createItem_up);
        itemNameEditText = findViewById(R.id.itemName_up);
        paymentTypeEditText = findViewById(R.id.paymentType_up);
        amountEditText = findViewById(R.id.amount_up);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int id = getIntent().getIntExtra("id", 0);
        int amount = getIntent().getIntExtra("amount", 0);
        String itemName = getIntent().getStringExtra("itemName");
        String paymentType = getIntent().getStringExtra("paymentType");

        itemNameEditText.setText(itemName);
        paymentTypeEditText.setText(paymentType);
        amountEditText.setText(String.valueOf(amount));

        createItem.setOnClickListener(v -> {
            ExpenseEntity entity = new ExpenseEntity();
            entity.id = id;
            entity.amount = Integer.parseInt(amountEditText.getText().toString());
            entity.itemName = itemNameEditText.getText().toString();
            entity.paymentType = paymentTypeEditText.getText().toString();
            entity.category = category;

            ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
            viewModel.update(entity);

            startActivity(new Intent(UpdateExpense.this, MainActivity.class));
        });

        deleteButton.setOnClickListener(v -> {
            ExpenseViewModel viewModel  =ViewModelProviders.of(this).get(ExpenseViewModel.class);
            viewModel.delete(id);
            startActivity(new Intent(UpdateExpense.this, MainActivity.class));
            finish();
        });
    }
}
