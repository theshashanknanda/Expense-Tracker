package com.example.expensetracker.Activities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Activities.Activities.UpdateExpense;
import com.example.expensetracker.R;

import java.util.List;

import Room.ExpenseEntity;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    public Context context;
    public List<ExpenseEntity> arrayList;

    public RecylerViewAdapter(Context context, List<ExpenseEntity> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylerview_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapter.ViewHolder holder, int position) {
        ExpenseEntity entity = arrayList.get(position);

        holder.itemName.setText(entity.itemName);
        holder.paymentType.setText(entity.paymentType);
        holder.amount.setText("-$"+String.valueOf(entity.amount));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), UpdateExpense.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", entity.id);
            intent.putExtra("itemName", entity.itemName);
            intent.putExtra("paymentType", entity.paymentType);
            intent.putExtra("amount", entity.amount);
            intent.putExtra("category", entity.category);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName, paymentType, amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName_rv);
            paymentType = itemView.findViewById(R.id.paymentType_rv);
            amount = itemView.findViewById(R.id.amount_rv);
        }
    }
}
