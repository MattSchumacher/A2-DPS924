package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductBaseAdapter extends BaseAdapter {
    List<Product> listOfProducts;
    Context context;

    public ProductBaseAdapter(List<Product> listOfProducts, Context context) {
        this.listOfProducts = listOfProducts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.base_adapter_row_layout, viewGroup, false);
        }

        // get current item to be displayed
        Product currentItem = (Product) getItem(i);

        TextView itemNameView = (TextView) view.findViewById(R.id.row_tv_item_name);
        TextView itemPriceView = (TextView) view.findViewById(R.id.row_tv_price);
        TextView itemQuantity = (TextView) view.findViewById(R.id.row_tv_quantity);

        itemNameView.setText(currentItem.name);
        itemPriceView.setText(String.valueOf(currentItem.price));
        itemQuantity.setText(String.valueOf(currentItem.quantity));

        return view;
    }
}
