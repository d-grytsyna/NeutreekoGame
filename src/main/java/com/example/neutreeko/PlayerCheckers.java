package com.example.neutreeko;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PlayerCheckers {
    private ImageView[] checkers = new ImageView[3];
    private Button[] btnCheckers = new Button[3];
    public PlayerCheckers(ImageView[] checkers, Button[] btnCheckers){
        this.checkers = checkers;
        this.btnCheckers = btnCheckers;
    }

    public Button[] getBtnCheckers() {
        return btnCheckers;
    }
    public void setBtnChecker(Button btn, int index){
        btnCheckers[index] = btn;
    }
    public ImageView[] getCheckers() {
        return checkers;
    }
}
