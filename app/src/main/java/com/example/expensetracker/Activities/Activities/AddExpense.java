package com.example.expensetracker.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import Room.ExpenseEntity;
import Room.ExpenseViewModel;

public class AddExpense extends AppCompatActivity {

    public Spinner spinner;
    public ImageView deleteButton;
    public Button createItem;
    public EditText itemNameEditText;
    public EditText paymentTypeEditText;
    public EditText amountEditText;

    public String itemName, paymentType, category = "Home";
    public int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spinner = findViewById(R.id.spinner);
        deleteButton = findViewById(R.id.deleteButton);
        createItem = findViewById(R.id.createItem_button);
        itemNameEditText = findViewById(R.id.itemName_editText);
        paymentTypeEditText = findViewById(R.id.paymentType_editText);
        amountEditText = findViewById(R.id.amount_editText);

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

        createItem.setOnClickListener(v -> {

            if(!itemNameEditText.getText().toString().isEmpty() &&
                    !paymentTypeEditText.getText().toString().isEmpty()
            && !amountEditText.getText().toString().isEmpty()){
                ExpenseEntity entity = new ExpenseEntity();
                entity.itemName = itemNameEditText.getText().toString();
                entity.paymentType = paymentTypeEditText.getText().toString();
                entity.amount = Integer.parseInt(amountEditText.getText().toString());
                entity.category = category;

                addItem(entity);
            }
            else{
                Toast.makeText(AddExpense.this, "Please fill the blanks", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            finish();
        });

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("totalExpense", String.valueOf(totalExpense));
//        editor.apply();
    }

    public void addItem(ExpenseEntity entity){
        ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        viewModel.insert(entity);

        List<ExpenseEntity> expenseList = new ArrayList<>();
        expenseList = viewModel.getItems;

        startActivity(new Intent(AddExpense.this, MainActivity.class));
        finish();
    }
}
