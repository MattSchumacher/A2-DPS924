package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", 1);
        HistoryManager historyManager = ((MyApp) getApplication()).historyManager;
        History historyItem = historyManager.getItem(id);

        TextView productNameTextView = findViewById(R.id.detail_tv_name);
        productNameTextView.setText(historyItem.name);

        TextView priceTextView = findViewById(R.id.detail_tv_price);
        priceTextView.setText(String.format("%.2f", historyItem.price));

        TextView dateTextView = findViewById(R.id.detail_tv_date);
        dateTextView.setText(historyItem.purchaseDate.toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}