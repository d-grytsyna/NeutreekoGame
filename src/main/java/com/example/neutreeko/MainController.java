package com.example.neutreeko;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainController {
    BoardCell[][] boardMatrix = new BoardCell[5][5];
    ArrayList<String[][]> stateMatrix = new ArrayList<>();
    PlayerCheckers playerCheckers;
    PlayerCheckers botCheckers;
    ImageView chosenChecker = null;
    Button btnChecker = null;
    int gameType = 2;
    boolean win = false;
    @FXML
    private Button Button11;
    @FXML
    private Button Button12;
    @FXML
    private Button Button13;
    @FXML
    private Button Button14;
    @FXML
    private Button Button15;
    @FXML
    private Button Button21;
    @FXML
    private Button Button22;
    @FXML
    private Button Button23;
    @FXML
    private Button Button24;
    @FXML
    private Button Button25;
    @FXML
    private Button Button31;
    @FXML
    private Button Button32;
    @FXML
    private Button Button33;
    @FXML
    private Button Button34;
    @FXML
    private Button Button35;
    @FXML
    private Button Button41;
    @FXML
    private Button Button42;
    @FXML
    private Button Button43;
    @FXML
    private Button Button44;
    @FXML
    private Button Button45;
    @FXML
    private Button Button51;
    @FXML
    private Button Button52;
    @FXML
    private Button Button53;
    @FXML
    private Button Button54;
    @FXML
    private Button Button55;
    @FXML
    private ImageView Black1;
    @FXML
    private ImageView Black2;
    @FXML
    private ImageView Black3;
    @FXML
    private ImageView White1;
    @FXML
    private ImageView White2;
    @FXML
    private ImageView White3;
    @FXML
    private Label InfoLabel;
    @FXML
    private Button RestartButton;
    @FXML
    void initialize(int type){
        boardMatrix[0][0] = new BoardCell(Button11, "", true);
        boardMatrix[0][1] = new BoardCell(Button12, "white", false);
        boardMatrix[0][2] = new BoardCell(Button13, "", true);
        boardMatrix[0][3] = new BoardCell(Button14, "white", false);
        boardMatrix[0][4] = new BoardCell(Button15, "", true);
        boardMatrix[1][0] = new BoardCell(Button21, "", true);
        boardMatrix[1][1] = new BoardCell(Button22, "", true);
        boardMatrix[1][2] = new BoardCell(Button23, "black", false);
        boardMatrix[1][3] = new BoardCell(Button24, "", true);
        boardMatrix[1][4] = new BoardCell(Button25, "", true);
        boardMatrix[2][0] = new BoardCell(Button31, "", true);
        boardMatrix[2][1] = new BoardCell(Button32, "", true);
        boardMatrix[2][2] = new BoardCell(Button33, "", true);
        boardMatrix[2][3] = new BoardCell(Button34, "", true);
        boardMatrix[2][4] = new BoardCell(Button35, "", true);
        boardMatrix[3][0] = new BoardCell(Button41, "", true);
        boardMatrix[3][1] = new BoardCell(Button42, "", true);
        boardMatrix[3][2] = new BoardCell(Button43, "white", false);
        boardMatrix[3][3] = new BoardCell(Button44, "", true);
        boardMatrix[3][4] = new BoardCell(Button45, "", true);
        boardMatrix[4][0] = new BoardCell(Button51, "", true);
        boardMatrix[4][1] = new BoardCell(Button52, "black", false);
        boardMatrix[4][2] = new BoardCell(Button53, "", true);
        boardMatrix[4][3] = new BoardCell(Button54, "black", false);
        boardMatrix[4][4] = new BoardCell(Button55, "", true);
        ImageView[] checkers = {Black1, Black2, Black3};
        Button[] checkersButtons = {Button23, Button52, Button54};
        playerCheckers = new PlayerCheckers(checkers, checkersButtons);
        ImageView[] checkersB = {White1, White2, White3};
        Button[] botCheckersButtons = {Button12, Button14, Button43};
        botCheckers = new PlayerCheckers(checkersB, botCheckersButtons);
        System.out.println("matrix set");
        gameType = type;
       // System.out.println(gameType);
    }
    @FXML
    public void chooseChecker(MouseEvent event){
        if(btnChecker!=null){
            btnChecker.setStyle("-fx-background-color: transparent; ");
        }
        ImageView checker = (ImageView)event.getSource();
        String id = checker.getId();
        for(int i=0; i<3; i++){
            if(id.equals(playerCheckers.getCheckers()[i].getId())){
            chosenChecker = playerCheckers.getCheckers()[i];
            btnChecker = playerCheckers.getBtnCheckers()[i];
            playerCheckers.getBtnCheckers()[i].setOpacity(1);
            playerCheckers.getBtnCheckers()[i].setStyle("-fx-background-color: #5f808a; ");
            }
        }
    }
    public String getCoordinates(Button btn){
        String res = "";
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(boardMatrix[i][j].getCellButton().getId().equals(btn.getId())){
                    res = i + " " + j;
                }
            }
        }
        return res;
    }
    @FXML
    public void makeStep(ActionEvent event){
        if(chosenChecker!=null){
        Button ChosenButton = (Button)event.getSource();
       // String id = ChosenButton.getId();
        //System.out.println(id);
        if(checkStepPosibility(btnChecker, ChosenButton, boardMatrix)){
            resetMatrix(btnChecker, ChosenButton, boardMatrix, "black");
            btnChecker.setStyle("-fx-background-color: transparent; ");
            Timeline checkerAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(0.7), new KeyValue(chosenChecker.layoutYProperty(), ChosenButton.getLayoutY())),
                    new KeyFrame(Duration.seconds(0.7), new KeyValue(chosenChecker.layoutXProperty(), ChosenButton.getLayoutX())));
            checkerAnimation.play();
            for(int i=0; i<3; i++){
                if(chosenChecker.getId().equals(playerCheckers.getCheckers()[i].getId())){
                    playerCheckers.setBtnChecker(ChosenButton, i);
                    ArrayList<String> coordinates = new ArrayList<>();
                    coordinates.add(getCoordinates(playerCheckers.getBtnCheckers()[0]));
                    coordinates.add(getCoordinates(playerCheckers.getBtnCheckers()[1]));
                    coordinates.add(getCoordinates(playerCheckers.getBtnCheckers()[2]));
                   // System.out.println("win " + checkWin(coordinates));
                    if(checkWin(coordinates)){
                        win = true;
                        endGame("player");
                        return;
                    }
                    chosenChecker = null;
                    btnChecker = null;
                    break;
                }
            }
            Black1.setDisable(true);
            Black2.setDisable(true);
            Black3.setDisable(true);
            setStateMatrix();
            checkDraw();
            resetCheckers();
            botGame();
        }
        }
    }
    public boolean checkStepPosibility(Button btnChecker, Button btnStep,BoardCell[][] matrix){
        boolean isPossible = false;
        String coordinatesChecker = "";
        String coordinatesStep = "";
        for(int i=0;i<5; i++){
           for(int j=0; j<5;j++){
               if(matrix[i][j].getCellButton().getId().equals(btnChecker.getId())) coordinatesChecker = i + " " + j;
               else if(matrix[i][j].getCellButton().getId().equals(btnStep.getId())) coordinatesStep = i + " " + j;
           }
        }
        String[] checkerXY = coordinatesChecker.split(" ");
        String[] stepXY = coordinatesStep.split(" ");
        if(Math.abs(Integer.parseInt(checkerXY[0])-Integer.parseInt(stepXY[0]))==Math.abs(Integer.parseInt(checkerXY[1])-Integer.parseInt(stepXY[1]))){
            isPossible = true;
            if((Integer.parseInt(checkerXY[0])>Integer.parseInt(stepXY[0]))&&(Integer.parseInt(checkerXY[1])>Integer.parseInt(stepXY[1]))){
                int j = Integer.parseInt(stepXY[1]);
                for(int i=Integer.parseInt(stepXY[0]); i<Integer.parseInt(checkerXY[0]); i++){
                    if(matrix[i][j].isAvaible()) j++;
                    else{
                        isPossible = false;
                        break;
                    }
                }
            }else if((Integer.parseInt(checkerXY[0])<Integer.parseInt(stepXY[0]))&&(Integer.parseInt(checkerXY[1])<Integer.parseInt(stepXY[1]))){
                int j = Integer.parseInt(checkerXY[1])+1;
                for(int i=Integer.parseInt(checkerXY[0])+1; i<=Integer.parseInt(stepXY[0]); i++){
                    if(matrix[i][j].isAvaible()) j++;
                    else{
                        isPossible = false;
                        break;
                    }
                }
            }else if((Integer.parseInt(checkerXY[0])<Integer.parseInt(stepXY[0]))&&(Integer.parseInt(checkerXY[1])>Integer.parseInt(stepXY[1]))){
                int j = Integer.parseInt(checkerXY[1])-1;
                for(int i=Integer.parseInt(checkerXY[0])+1; i<=Integer.parseInt(stepXY[0]); i++){
                    if(matrix[i][j].isAvaible()) j--;
                    else{
                        isPossible = false;
                        break;
                    }
                }
            }else{
                int j = Integer.parseInt(checkerXY[1])+1;
                for(int i=Integer.parseInt(checkerXY[0])-1; i>=Integer.parseInt(stepXY[0]); i--){
                    if(matrix[i][j].isAvaible()) j++;
                    else{
                        isPossible = false;
                        break;
                    }
                }
            }
        }else if(Integer.parseInt(checkerXY[0])==Integer.parseInt(stepXY[0])){
            isPossible = true;
            if(Integer.parseInt(checkerXY[1])>Integer.parseInt(stepXY[1])){
                for(int i=Integer.parseInt(stepXY[1]); i<Integer.parseInt(checkerXY[1]); i++){
                    if(!matrix[Integer.parseInt(checkerXY[0])][i].isAvaible()){
                        isPossible = false;
                        break;
                    }
                }
            }else{
                for(int i=Integer.parseInt(stepXY[1]); i>Integer.parseInt(checkerXY[1]); i--){
                    if(!matrix[Integer.parseInt(checkerXY[0])][i].isAvaible()){
                        isPossible = false;
                        break;
                    }
                }
            }
        }else if(Integer.parseInt(checkerXY[1])==Integer.parseInt(stepXY[1])){
            isPossible = true;
            if(Integer.parseInt(checkerXY[0])>Integer.parseInt(stepXY[0])){
                for(int i=Integer.parseInt(stepXY[0]); i<Integer.parseInt(checkerXY[0]); i++){
                    if(!matrix[i][Integer.parseInt(checkerXY[1])].isAvaible()){
                        isPossible = false;
                        break;
                    }
                }
            }else{
                for(int i=Integer.parseInt(stepXY[0]); i>Integer.parseInt(checkerXY[0]); i--){
                    if(!matrix[i][Integer.parseInt(checkerXY[1])].isAvaible()){
                        isPossible = false;
                        break;
                    }
                }
            }

        }
        return isPossible;
    }
    public void resetMatrix(Button btnCheker, Button btnStep, BoardCell[][] matrix, String checker){
        int iCheker = 0;
        int jCheker = 0;
        int iStep = 0;
        int jStep = 0;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(matrix[i][j].getCellButton().getId()==btnCheker.getId()){
                    iCheker = i;
                    jCheker = j;
                }
                if(matrix[i][j].getCellButton().getId()==btnStep.getId()){
                    iStep = i;
                    jStep = j;
                }
            }
        }
        String checkerStep = matrix[iCheker][jCheker].getContainsChecker();
        matrix[iStep][jStep].setCellInfo(checkerStep, false);
        matrix[iCheker][jCheker].setCellInfo("", true);
    }
    public void botGame(){
        switch (gameType){
            case 0:
                ArrayList<Button> avaibleButtons  = new ArrayList<>();
                int randomChecker = 0;
                while (avaibleButtons.isEmpty()) {
                    randomChecker = ThreadLocalRandom.current().nextInt(0, 3);
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if(!botCheckers.getBtnCheckers()[randomChecker].getId().equals(boardMatrix[i][j].getCellButton().getId())){
                            if (checkStepPosibility(botCheckers.getBtnCheckers()[randomChecker], boardMatrix[i][j].getCellButton(), boardMatrix) )avaibleButtons.add(boardMatrix[i][j].getCellButton());
                            }
                        }
                    }
                }
                int randomStep = ThreadLocalRandom.current().nextInt(0, avaibleButtons.size());
                resetMatrix(botCheckers.getBtnCheckers()[randomChecker], avaibleButtons.get(randomStep), boardMatrix, "white");
                Timeline checkerAnimation = new Timeline(
                        new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[randomChecker].layoutYProperty(), avaibleButtons.get(randomStep).getLayoutY())),
                        new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[randomChecker].layoutXProperty(), avaibleButtons.get(randomStep).getLayoutX())));
                checkerAnimation.setDelay(Duration.seconds(1.2));
                checkerAnimation.play();
                botCheckers.setBtnChecker(avaibleButtons.get(randomStep), randomChecker);
            break;
            case 1:
                ArrayList<Step> allSteps = new ArrayList<>();
                ArrayList<Step> arr1 = getSteps(boardMatrix, botCheckers.getBtnCheckers()[0], "bot");
                ArrayList<Step> arr2= getSteps(boardMatrix, botCheckers.getBtnCheckers()[1], "bot");
                ArrayList<Step> arr3= getSteps(boardMatrix, botCheckers.getBtnCheckers()[2], "bot");
                allSteps.addAll(arr1);
                allSteps.addAll(arr2);
                allSteps.addAll(arr3);
                ArrayList<Step> stepsChosen =  new ArrayList<>();
                stepsChosen = getBest(5, allSteps);
                int randomIndex = ThreadLocalRandom.current().nextInt(0, stepsChosen.size());
                for(int i=0; i<stepsChosen.size(); i++){
                    if(stepsChosen.get(i).getF()==Integer.MAX_VALUE){
                        randomIndex = i;
                        break;
                    }
                }
                int randbtncheckIndex = 0;
                for (int i = 0; i < 3; i++) {
                    if (botCheckers.getBtnCheckers()[i].getId().equals(stepsChosen.get(randomIndex).getChecker().getId()))
                        randbtncheckIndex= i;
                }
                resetMatrix(stepsChosen.get(randomIndex).getChecker(), stepsChosen.get(randomIndex).getStep(), boardMatrix, "white");
                Timeline checkerAnimation1 = new Timeline(
                        new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[randbtncheckIndex].layoutYProperty(), stepsChosen.get(randomIndex).getStep().getLayoutY())),
                        new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[randbtncheckIndex].layoutXProperty(), stepsChosen.get(randomIndex).getStep().getLayoutX())));
                checkerAnimation1.setDelay(Duration.seconds(1.2));
                checkerAnimation1.play();
                botCheckers.setBtnChecker(stepsChosen.get(randomIndex).getStep(), randbtncheckIndex);
            break;
            case 2:
                CheckNode start = setTree();
                ArrayList<CheckNode> path = new ArrayList<>();
                boolean checkWin = false;
                int index = 0;
                if(start.getChildren().isEmpty()){
                    win = true;
                    endGame(" player, absolutely!");
                }else {
                    for (int i = 0; i < start.getChildren().size(); i++) {
                        if (start.getChildren().get(i).getStep().getF() == Integer.MAX_VALUE) {
                            checkWin = true;
                            index = i;
                        }
                    }
                    if (!checkWin) {
                        SearchResult search = alphabeta(start, Integer.MIN_VALUE, Integer.MAX_VALUE, true, path);
                       // System.out.println(search.value);
                        for (int i = 0; i < start.getChildren().size(); i++) {
                            if (start.getChildren().get(i).getStep().getF() == search.value) {
                                index = i;
                            }
                        }
                    }
                    int btncheckIndex = 0;
                    for (int i = 0; i < 3; i++) {
                        if (botCheckers.getBtnCheckers()[i].getId().equals(start.getChildren().get(index).getStep().getChecker().getId()))
                            btncheckIndex = i;
                    }
                    resetMatrix(start.getChildren().get(index).getStep().getChecker(), start.getChildren().get(index).getStep().getStep(), boardMatrix, "white");
                    Timeline checkerAnimation2 = new Timeline(
                            new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[btncheckIndex].layoutYProperty(), start.getChildren().get(index).getStep().getStep().getLayoutY())),
                            new KeyFrame(Duration.seconds(0.7), new KeyValue(botCheckers.getCheckers()[btncheckIndex].layoutXProperty(), start.getChildren().get(index).getStep().getStep().getLayoutX())));
                    checkerAnimation2.setDelay(Duration.seconds(1.2));
                    checkerAnimation2.play();
                    botCheckers.setBtnChecker(start.getChildren().get(index).getStep().getStep(), btncheckIndex);
                }
            break;
        }
        ArrayList<String> coordinates = new ArrayList<>();
        coordinates.add(getCoordinates(botCheckers.getBtnCheckers()[0]));
        coordinates.add(getCoordinates(botCheckers.getBtnCheckers()[1]));
        coordinates.add(getCoordinates(botCheckers.getBtnCheckers()[2]));
        if(checkWin(coordinates)){
            win = true;
            endGame("bot");
        }
        setStateMatrix();
        if(checkDraw()) endGame("no one, it's draw");
    }
    public void resetCheckers(){
        Thread threadReverse = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run(){
                        if(!win){
                        Black1.setDisable(false);
                        Black2.setDisable(false);
                        Black3.setDisable(false);
                        }

                    }
                });
            }
        };
        threadReverse.start();
    }
    public SearchResult alphabeta(CheckNode node, int a, int b, boolean maximizing, ArrayList<CheckNode> path){
         if(node.getChildren().isEmpty()) return new SearchResult(node.getStep().getF(), path, node);
         if(maximizing){
             int value = Integer.MIN_VALUE;
             for(CheckNode child:node.getChildren()){
                 path.add(child);
                 value = max(value, alphabeta(child,a,b,false, path).value);
                 child.getStep().setF(value);
                 if(value>b) break;
                 a = max(a, value);
             }
             return new SearchResult(value, path, node);
         }else{
             int value = Integer.MAX_VALUE;
             for(CheckNode child:node.getChildren()){
                 path.add(child);
                 value = min(value, alphabeta(child,a,b, true, path).value);
                 child.getStep().setF(value);
                 if(value<a) break;
                 b = min(b, value);
             }
             return new SearchResult(value, path, node);

         }
    }
    public int max(int v1, int v2){
        if(v1>v2) return v1;
        else return v2;
    }
    public int min(int v1, int v2){
        if(v1<v2) return v1;
        else return v2;
    }
    public void checkMatrix(BoardCell[][] matrix){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(matrix[i][j].getContainsChecker().equals(""))matrix[i][j].setAvaible(true);
            }
        }
    }
    public CheckNode setTree(){
        ArrayList<CheckNode> children = new ArrayList<>();
        ArrayList<Step> bestSteps = generateAllSteps(copyMatrix(boardMatrix),botCheckers.getBtnCheckers(), "bot");
        for(int i=0; i<bestSteps.size(); i++){
            BoardCell[][] newMatrix = copyMatrix(boardMatrix);
            resetMatrix(bestSteps.get(i).getChecker(), bestSteps.get(i).getStep(), newMatrix, "white");
            checkMatrix(newMatrix);
            ArrayList<Step> worstSteps = getPlayerMoves(copyMatrix(newMatrix));
            if(bestSteps.get(i).getF()==Integer.MAX_VALUE){
                CheckNode winnerChild = new CheckNode(boardMatrix, bestSteps.get(i), new ArrayList<>());
                children.add(winnerChild);
                return new CheckNode( boardMatrix, new Step(null, null,0), children);
            }
            if(worstSteps.get(0).getF()==Integer.MIN_VALUE){
                bestSteps.remove(i);
                i--;
            }else{
            ArrayList<CheckNode> child2Arr = new ArrayList<>();
                for (int j = 0; j < worstSteps.size(); j++) {
                    BoardCell[][] newMatrix2 = copyMatrix(newMatrix);
                    resetMatrix(worstSteps.get(j).getChecker(), worstSteps.get(j).getStep(), newMatrix2, "black");
                    ArrayList<Step> bestSteps2 = generateAllSteps(copyMatrix(newMatrix2), getPlayerCheckers(newMatrix2, "white"), "bot");
                    ArrayList<CheckNode> child3Arr = new ArrayList<>();
                    for (int k = 0; k < bestSteps2.size(); k++) {
                        BoardCell[][] newMatrix3 = copyMatrix(newMatrix2);
                        resetMatrix(bestSteps2.get(k).getChecker(), bestSteps2.get(k).getStep(), newMatrix3, "white");
                        CheckNode child3 = new CheckNode(newMatrix3, bestSteps2.get(k), new ArrayList<>());
                        child3Arr.add(child3);
                    }
                    CheckNode child2 = new CheckNode(newMatrix2, worstSteps.get(j), child3Arr);
                    child2Arr.add(child2);

                }
                CheckNode child = new CheckNode(newMatrix, bestSteps.get(i), child2Arr);
                children.add(child);
            }
        }
        CheckNode start = new CheckNode( boardMatrix, new Step(null, null,0), children);
        return start;
    }
    public ArrayList<Step> getPlayerMoves(BoardCell[][] matrix){
        ArrayList<Step> worstSteps = new ArrayList<>();
        Button[] pChekers = getPlayerCheckers(matrix, "black");
        worstSteps = generateAllSteps(matrix, pChekers, "player");
        return worstSteps;
    }
    public Button[] getPlayerCheckers(BoardCell[][] matrix, String type){
        Button[] getChekerBtns = new Button[3];
        int k = 0;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(matrix[i][j].getContainsChecker().equals(type)){
                    getChekerBtns[k] = matrix[i][j].getCellButton();
                    k++;
                }
            }
        }
        return getChekerBtns;
    }
    public BoardCell[][] copyMatrix(BoardCell[][] matrix){
        BoardCell[][] copymatrix = new BoardCell[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                copymatrix[i][j] = new BoardCell(null, null, true);
                copymatrix[i][j].setAvaible(matrix[i][j].isAvaible());
                copymatrix[i][j].setCellButton(matrix[i][j].getCellButton());
                copymatrix[i][j].setContainsChecker(matrix[i][j].getContainsChecker());
            }
        }
        return copymatrix;
    }
    public ArrayList<Step> generateAllSteps(BoardCell[][] matrix, Button[] checkers, String type){
            ArrayList<Step> allSteps = new ArrayList<>();
            ArrayList<Step> arr1 = getSteps(matrix, checkers[0], type);
            ArrayList<Step> arr2= getSteps(matrix, checkers[1], type);
            ArrayList<Step> arr3= getSteps(matrix, checkers[2], type);
            allSteps.addAll(arr1);
            allSteps.addAll(arr2);
            allSteps.addAll(arr3);
            ArrayList<Step> stepsChosen =  new ArrayList<>();
            if(type.equals("bot")) stepsChosen = getBest(allSteps.size(), allSteps);
            else stepsChosen = getWorst(allSteps.size(), allSteps);
            return stepsChosen;
    }
    public ArrayList<Step> getBest(int amount, ArrayList<Step> steps){
        ArrayList<Step> result = new ArrayList<>();
        for(int i=0; i<amount; i++){
            int maxF = steps.get(0).getF();
            int maxIndex = 0;
            for(int j=1; j<steps.size(); j++){
                if(steps.get(j).getF()>maxF){
                   maxF = steps.get(j).getF();
                   maxIndex = j;
                }
            }
            result.add(steps.get(maxIndex));
            steps.remove(maxIndex);
        }
        return result;
    }
    public ArrayList<Step> getWorst(int amount, ArrayList<Step> steps){
        ArrayList<Step> result = new ArrayList<>();
        for(int i=0; i<amount; i++){
            int minF = steps.get(0).getF();
            int minIndex = 0;
            for(int j=1; j<steps.size(); j++){
                if(steps.get(j).getF()<minF){
                    minF = steps.get(j).getF();
                    minIndex = j;
                }
            }
            result.add(steps.get(minIndex));
            steps.remove(minIndex);
        }
        return result;
    }
    public ArrayList<Step> getSteps(BoardCell[][] matrix, Button btnChecker, String type){
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Button> avaibleMoves = new ArrayList<>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(!btnChecker.getId().equals(matrix[i][j].getCellButton().getId())){
                if(checkStepPosibility(btnChecker, matrix[i][j].getCellButton(), matrix)){
                    avaibleMoves.add(matrix[i][j].getCellButton());
                }
                }
            }
        }
        for(int i=0; i<avaibleMoves.size(); i++){
            Step newStep = new Step(btnChecker, avaibleMoves.get(i), evaluateF(btnChecker, avaibleMoves.get(i), matrix, type));
            steps.add(newStep);
        }
        return steps;
    }
    public int evaluateF(Button checker, Button step, BoardCell[][] matrix, String type){
        String myChecker = "white";
        String notmyChekcer = "black";
        if(type.equals("player")){
            myChecker = "black";
            notmyChekcer = "white";
        }
        int result = 0;
        BoardCell[][] copymatrix = new BoardCell[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                copymatrix[i][j] = new BoardCell(null, null, true);
                copymatrix[i][j].setAvaible(matrix[i][j].isAvaible());
                copymatrix[i][j].setCellButton(matrix[i][j].getCellButton());
                copymatrix[i][j].setContainsChecker(matrix[i][j].getContainsChecker());
            }
        }
        resetMatrix(checker, step, copymatrix, myChecker);
        ArrayList<Button> playerChecks = new ArrayList<>();
        ArrayList<String> coordinates = new ArrayList<>();
        ArrayList<Button> botChecks = new ArrayList<>();
        String stepCoordinates = "";
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(matrix[i][j].getContainsChecker().equals(notmyChekcer)) playerChecks.add(matrix[i][j].getCellButton());
                if(matrix[i][j].getCellButton().getId().equals(step.getId())||matrix[i][j].getContainsChecker().equals(myChecker)&&!(matrix[i][j].getCellButton().getId().equals(checker.getId()))){
                    coordinates.add(i+" "+j);
                    botChecks.add(matrix[i][j].getCellButton());
                }
                if(matrix[i][j].getCellButton().getId().equals(step.getId())){
                    stepCoordinates= i + " " + j;
                }
            }
        }
        int movesPosibilitiesPlayer = 0;
        for(int k=0; k<3;k++) {
            ArrayList<Button> avaibleButtons  = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(!playerChecks.get(k).getId().equals(matrix[i][j].getCellButton().getId())){
                        if (checkStepPosibility(playerChecks.get(k), matrix[i][j].getCellButton(), matrix))
                            avaibleButtons.add(matrix[i][j].getCellButton());
                    }
                }
            }
            movesPosibilitiesPlayer += avaibleButtons.size();
        }
        int botMatches = 0;
            String[] w1 = coordinates.get(0).split(" ");
            String[] w2 = coordinates.get(1).split(" ");
            String[] w3 = stepCoordinates.split(" ");
            if(Math.abs(Integer.parseInt(w1[0])-Integer.parseInt(w2[0]))==Math.abs(Integer.parseInt(w1[1])-Integer.parseInt(w2[1]))||(Integer.parseInt(w1[0])==Integer.parseInt(w2[0]))||(Integer.parseInt(w1[1])==Integer.parseInt(w2[1]))){
                copymatrix[Integer.parseInt(w1[0])][Integer.parseInt(w1[1])].setAvaible(true);
                if(checkStepPosibility(botChecks.get(1), botChecks.get(0), copymatrix)) botMatches += 20;
                else botMatches += 10;
                copymatrix[Integer.parseInt(w1[0])][Integer.parseInt(w1[1])].setAvaible(false);
            }
            if(Math.abs(Integer.parseInt(w1[0])-Integer.parseInt(w3[0]))==Math.abs(Integer.parseInt(w1[1])-Integer.parseInt(w3[1]))||(Integer.parseInt(w1[0])==Integer.parseInt(w3[0]))||(Integer.parseInt(w1[1])==Integer.parseInt(w3[1]))){
                copymatrix[Integer.parseInt(w1[0])][Integer.parseInt(w1[1])].setAvaible(true);
                if(checkStepPosibility(botChecks.get(2), botChecks.get(0), copymatrix)) botMatches +=20;
                else botMatches += 10;
                copymatrix[Integer.parseInt(w1[0])][Integer.parseInt(w1[1])].setAvaible(false);
            }
            if(Math.abs(Integer.parseInt(w2[0])-Integer.parseInt(w3[0]))==Math.abs(Integer.parseInt(w2[1])-Integer.parseInt(w3[1]))||(Integer.parseInt(w2[0])==Integer.parseInt(w3[0]))||(Integer.parseInt(w2[1])==Integer.parseInt(w3[1]))){
                copymatrix[Integer.parseInt(w2[0])][Integer.parseInt(w2[1])].setAvaible(true);
                if(checkStepPosibility(botChecks.get(2), botChecks.get(1), copymatrix)) botMatches += 20;
                else botMatches+= 10;
                copymatrix[Integer.parseInt(w2[0])][Integer.parseInt(w2[1])].setAvaible(false);
            }
        result = botMatches - (movesPosibilitiesPlayer/10);
        if(myChecker.equals("white")){
            if(checkHalfWin(Integer.parseInt(w1[0]), Integer.parseInt(w1[1]),
                    Integer.parseInt(w2[0]),
                    Integer.parseInt(w2[1]),
                    Integer.parseInt(w3[0]),
                    Integer.parseInt(w3[1]))){
                result += 1000;
            }
        }
        coordinates.add(stepCoordinates);
        if(checkWin(coordinates)){
            if(type.equals("player"))return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
        if(type.equals("player")) return -result;
        else return result;
    }
    public boolean checkHalfWin(int x1, int y1, int x2, int y2, int x3, int y3){
        if((x3+1==x1&&y1==y3)||
           (x1+1==x3&&y1==y3)||
            (x2+1==x3&&y2==y3)||
            (x3+1==x2&&y2==y3)||
            (y3+1==y1&&x1==x3)||
            (y1+1==y3&&x1==x3)||
            (y2+1==y3&&x2==x3)||
            (y3+1==y2&&x2==x3)||
            (x3+1==x2&&y3+1==y2)||
            (x2+1==x3&&y2+1==y3)||
            (x3-1==x2&&y3+1==y2)||
            (x2-1==x3&&y2+1==y3)||
            (x3+1==x2&&y3-1==y2)||
            (x2+1==x3&&y2-1==y3)||//x2x3
            (x3+1==x1&&y3+1==y1)||
            (x1+1==x3&&y1+1==y3)||
            (x3-1==x1&&y3+1==y1)||
            (x1-1==x3&&y1+1==y3)||
            (x3+1==x1&&y3-1==y1)||
            (x1+1==x3&&y1-1==y3)
        ) return true;
        else return false;
    }
    public boolean checkWin(ArrayList<String> coordinates){
        boolean win = false;
        String[] coordinates1 = coordinates.get(0).split(" ");
        String[] coordinates2 = coordinates.get(1).split(" ");
        String[] coordinates3 = coordinates.get(2).split(" ");
        int x1 = Integer.parseInt(coordinates1[0]);
        int y1 = Integer.parseInt(coordinates1[1]);
        int x2 = Integer.parseInt(coordinates2[0]);
        int y2 = Integer.parseInt(coordinates2[1]);
        int x3 = Integer.parseInt(coordinates3[0]);
        int y3 = Integer.parseInt(coordinates3[1]);
        if((x1+1==x2&&x2+1==x3&&y1==y2&&y2==y3)||
            (x1+1==x3&&x3+1==x2&&y1==y2&&y2==y3)||
            (x2+1==x1&&x1+1==x3&&y1==y2&&y2==y3)||
            (x2+1==x3&&x3+1==x1&&y1==y2&&y2==y3)||
            (x3+1==x1&&x1+1==x2&&y1==y2&&y2==y3)||
            (x3+1==x2&&x2+1==x1&&y1==y2&&y2==y3)
        ){
          //  System.out.println("win vertical");
            win = true;
        }else if(
                (y1+1==y2&&y2+1==y3&&x1==x2&&x2==x3)||
                (y1+1==y3&&y3+1==y2&&x1==x2&&x2==x3)||
                (y2+1==y1&&y1+1==y3&&x1==x2&&x2==x3)||
                (y2+1==y3&&y3+1==y1&&x1==x2&&x2==x3)||
                (y3+1==y1&&y1+1==y2&&x1==x2&&x2==x3)||
                (y3+1==y2&&y2+1==y1&&x1==x2&&x2==x3)
        ){
            //System.out.println("win horizontal");
            win = true;
        }else if((x1+1==x2&&x2+1==x3&&y1+1==y2&&y2+1==y3)||
                (x2+1==x1&&x1+1==x3&&y2+1==y1&&y1+1==y3)||
                (x3+1==x2&&x2+1==x1&&y3+1==y2&&y2+1==y1)||
                (x1+1==x3&&x3+1==x2&&y1+1==y3&&y3+1==y2)||
                (x2+1==x3&&x3+1==x1&&y2+1==y3&&y3+1==y1)||
                (x3+1==x1&&x1+1==x2&&y3+1==y1&&y1+1==y2)||
                (x1+1==x2&&x2+1==x3&&y1-1==y2&&y2-1==y3)||
                (x2+1==x1&&x1+1==x3&&y2-1==y1&&y1-1==y3)||
                (x3+1==x2&&x2+1==x1&&y3-1==y2&&y2-1==y1)||
                (x1+1==x3&&x3+1==x2&&y1-1==y3&&y3-1==y2)||
                (x2+1==x3&&x3+1==x1&&y2-1==y3&&y3-1==y1)||
                (x3+1==x1&&x1+1==x2&&y3-1==y1&&y1-1==y2)||
                (x1-1==x2&&x2-1==x3&&y1+1==y2&&y2+1==y3)||
                (x2-1==x1&&x1-1==x3&&y2+1==y1&&y1+1==y3)||
                (x3-1==x2&&x2-1==x1&&y3+1==y2&&y2+1==y1)||
                (x1-1==x3&&x3-1==x2&&y1+1==y3&&y3+1==y2)||
                (x2-1==x3&&x3-1==x1&&y2+1==y3&&y3+1==y1)||
                (x3-1==x1&&x1-1==x2&&y3+1==y1&&y1+1==y2)
        )
        {
           // System.out.println("win diagonal");
            win = true;
        }else{
          //  System.out.println("not win");
        }
        return win;
    }
    public void setStateMatrix(){
        String[][] newState = new String[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                newState[i][j] = boardMatrix[i][j].getContainsChecker();
            }
        }
        stateMatrix.add(newState);
    }
    public boolean checkDraw(){
        boolean isDraw = false;
        for(int i=0; i<stateMatrix.size(); i++){
            int counter = 0;
            for(int j=i+1; j<stateMatrix.size(); j++){
                if(isSame(stateMatrix.get(i), stateMatrix.get(j))) counter++;
            }
            if(counter>=3){
                isDraw = true;
                break;
            }
        }
        return isDraw;
    }
    public boolean isSame(String[][] matrix1, String[][] matrix2){
        boolean isSame = true;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(!matrix1[i][j].equals(matrix2[i][j])) isSame = false;
            }
        }
        return  isSame;
    }
    public void endGame(String winner){
        Black1.setDisable(true);
        Black2.setDisable(true);
        Black3.setDisable(true);
        InfoLabel.setText("Winner is " + winner);
        RestartButton.setLayoutY(630);
    }
    @FXML
    public void restartGame(ActionEvent event) throws IOException {
        Node StartBtn = (Node) event.getSource();
        Stage stage = (Stage) StartBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("main-view.fxml"));
        Parent fxmlLoaderP = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();
        controller.initialize(gameType);
        stage.setTitle("Neutreeko");
        stage.setScene(new Scene(fxmlLoaderP, 700, 700));
        stage.show();
    }
}