package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class RestockActivity extends AppCompatActivity {
    ProductManager productManager;
    ListView productListView;
    Product selectedProduct;
    ProductBaseAdapter adapter;
    EditText quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        quantityEditText = findViewById(R.id.restock_et_quantity);

        productManager = ((MyApp) getApplication()).productManager;
        List<Product> allProducts = productManager.getAllProducts();

        productListView = findViewById(R.id.restock_lv_items);
        productListView.setOnItemClickListener((parent, view, position, id) -> {

            Product currentProduct = (Product) parent.getAdapter().getItem(position);
            selectedProduct = currentProduct;
        });


        adapter = new ProductBaseAdapter(allProducts, this);
        productListView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.notifyDataSetChanged();
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

    public void onOkClick(View _) {
        String quantityContent = quantityEditText.getText().toString();
        if (quantityContent.isEmpty() || selectedProduct == null){
            String text = "All fields are REQUIRED!";

            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Integer value = Integer.valueOf(quantityContent);
        selectedProduct.quantity += value;
        adapter.notifyDataSetChanged();
    }

    public  void onCancelClick(View _){
        onBackPressed();
    }
}