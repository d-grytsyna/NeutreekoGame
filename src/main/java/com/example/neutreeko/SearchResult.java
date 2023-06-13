package com.example.neutreeko;

import java.util.ArrayList;

public class SearchResult {
    public int value;
    public ArrayList<CheckNode> path;
    public CheckNode node;
    public SearchResult(int value, ArrayList<CheckNode> path, CheckNode node){
        this.value = value;
        this.path = path;
        this.node = node;
    }
}
