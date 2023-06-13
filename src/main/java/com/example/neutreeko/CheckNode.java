package com.example.neutreeko;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class CheckNode {
    private ArrayList<CheckNode> children;
    private BoardCell[][] boardMatrix;
    private Step step;
    public CheckNode( BoardCell[][] boardMatrix, Step step, ArrayList<CheckNode> children){
        this.boardMatrix = boardMatrix;
        this.step = step;
        this.children = children;

    }
    public ArrayList<CheckNode> getChildren() {
        return children;
    }
    public Step getStep(){return step;}
    public void setChildren(ArrayList<CheckNode> children) {
        this.children = children;
    }
}
