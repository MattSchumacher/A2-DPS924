package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productNameTextView;
        public TextView quantityTextView;
        public TextView priceTextView;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            productNameTextView = itemView.findViewById(R.id.history_row_product_name);
            quantityTextView = itemView.findViewById(R.id.history_row_quantity);
            priceTextView = itemView.findViewById(R.id.history_row_price);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                History history = allHistory.get(position);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", history.id);
                context.startActivity(intent);
            }
        }
    }

    private List<History> allHistory;

    public HistoryAdapter(List<History> historyItems) {
        allHistory = historyItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View historyRow = inflater.inflate(R.layout.history_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(context, historyRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History historyItem = allHistory.get(position);

        TextView nameTextView = holder.productNameTextView;
        nameTextView.setText(historyItem.name);

        TextView priceTextView = holder.priceTextView;
        priceTextView.setText(String.format("%.2f", historyItem.price));

        TextView quantityTextView = holder.quantityTextView;
        quantityTextView.setText(String.valueOf(historyItem.quantity));
    }

    @Override
    public int getItemCount() {
        if (allHistory != null) {
            return allHistory.size();
        }

        return 0;
    }
}
