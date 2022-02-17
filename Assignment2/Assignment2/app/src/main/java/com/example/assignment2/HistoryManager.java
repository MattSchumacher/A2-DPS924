package com.example.assignment2;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<History> allHistory = new ArrayList<>();
    Integer currentId = 0;

    public List<History> getAllHistory() {
        return allHistory;
    }

    public void addHistory(Product product, Integer quantity) {
        // update the price


        History newHistory = new History(currentId, product, quantity);
        allHistory.add(newHistory);

        currentId++;
    }

    public History getItem(Integer id) {
        return allHistory.get(id);
    }
}
