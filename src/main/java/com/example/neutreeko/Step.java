package com.example.neutreeko;

import javafx.scene.control.Button;

public class Step {
    private Button checker;
    private Button step;
    private int F;
    public Step(Button checker, Button step, int F){
        this.checker = checker;
        this.step = step;
        this.F = F;
    }
    public int getF(){
        return F;
    }
    public void setF(int F){
        this.F = F;
    }

    public Button getStep() {
        return step;
    }

    public Button getChecker() {
        return checker;
    }
}
