package com.example.assignment2;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    NumberPicker numberPicker;
    TextView quantity;
    TextView productTextView;
    TextView totalTextView;
    ProductManager productManager;
    ListView baseAdapterList;
    Product selectedProduct;
    Integer selectedQuantity = 0;
    Double selectedTotal;
    ProductBaseAdapter adapter;
    HistoryManager historyManager;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        productTextView = findViewById(R.id.main_tv_product);
        totalTextView = findViewById(R.id.main_tv_total);

        numberPicker = findViewById(R.id.main_np_main);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setOnValueChangedListener(this);

        quantity = findViewById(R.id.main_tv_quantity);
        baseAdapterList = findViewById(R.id.main_ll_products);


        baseAdapterList.setOnItemClickListener((parent, view, position, id) -> {

            Product currentProduct = (Product) parent.getAdapter().getItem(position);
            selectedProduct = currentProduct;
            productTextView.setText(currentProduct.name);
            updateTotal();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        quantity.setText(R.string.main_tv_quantity);

        productManager = ((MyApp) getApplication()).productManager;
        List<Product> allProducts = productManager.getAllProducts();

        adapter = new ProductBaseAdapter(allProducts, this);
        baseAdapterList.setAdapter(adapter);

        historyManager = ((MyApp) getApplication()).historyManager;

        resetUI();
    }

    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        quantity.setText(String.valueOf(newVal));
        selectedQuantity = newVal;
        updateTotal();
    }

    public void onManagerClick(View _) {
        Intent intent = new Intent(this, ManagerActivity.class);
        startActivity(intent);
    }

    private void updateTotal() {
        if (selectedProduct != null) {
            selectedTotal = selectedProduct.price * selectedQuantity;
            totalTextView.setText(String.format("%.2f", selectedTotal));
        }
    }

    public void onBuyClick(View _) {
        Context context = getApplicationContext();

        if (selectedProduct == null || selectedQuantity == 0) {
                String text = "You must select product and quantity!";

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
                return;
        }

        if (selectedQuantity > selectedProduct.quantity) {
            String text = "Not enough quantity in the stock!!!";

            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        selectedProduct.quantity -= selectedQuantity;
        adapter.notifyDataSetChanged();

        historyManager.addHistory(selectedProduct, selectedQuantity);

        new AlertDialog.Builder(this)
                .setTitle("Thank You For Your Purchase")
                .setMessage(String.format("Your purchase is %d %s for %.2f", selectedQuantity, selectedProduct.name,selectedTotal))
                .show();

        resetUI();
    }

    private void resetUI(){
        selectedTotal = 0.0;
        selectedProduct = null;
        selectedQuantity = 0;
        numberPicker.setValue(0);

        totalTextView.setText(R.string.main_tv_total);
        productTextView.setText(R.string.main_tv_product);
        quantity.setText(R.string.main_tv_quantity);
    }
}