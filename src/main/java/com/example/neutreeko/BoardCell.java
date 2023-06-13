package com.example.neutreeko;

import javafx.scene.control.Button;

public class BoardCell {
    private Button CellButton;
    private String containsChecker;
    private boolean isAvaible;
    public BoardCell(Button CellButton, String containsChecker, boolean isAvaible){
        this.CellButton = CellButton;
        this.containsChecker = containsChecker;
        this.isAvaible = isAvaible;
    }
    public Button getCellButton() {
        return CellButton;
    }
    public boolean isAvaible() {
        return isAvaible;
    }

    public String getContainsChecker() {
        return containsChecker;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
    }

    public void setCellButton(Button cellButton) {
        CellButton = cellButton;
    }

    public void setContainsChecker(String containsChecker) {
        this.containsChecker = containsChecker;
    }

    public void setCellInfo(String containsChecker, boolean isAvaible){
        this.containsChecker = containsChecker;
        this.isAvaible = isAvaible;
    }
}
