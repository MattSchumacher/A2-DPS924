package com.example.assignment2;

import android.app.Application;

public class MyApp extends Application {
    ProductManager productManager = new ProductManager();
    HistoryManager historyManager = new HistoryManager();
}
