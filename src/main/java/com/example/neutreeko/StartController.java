package com.example.neutreeko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    private static int gameType = -1;
    @FXML
    private Button DifficultButton;

    @FXML
    private Button EasyButton;

    @FXML
    private Button MediumButton;

    @FXML
    private Button StartButton;
    @FXML
    void initialize(){
        StartButton.setDisable(true);
    }
    @FXML
    public void chosenType(ActionEvent event){
        Button ButtonType = (Button)event.getSource();
        if(ButtonType.getId().equals(DifficultButton.getId())) {
            DifficultButton.setStyle("-fx-background-color: #5f808a; ");
            EasyButton.setStyle("-fx-background-color:  #458788; ");
            MediumButton.setStyle("-fx-background-color:  #458788; ");
            gameType = 2;
        }else if(ButtonType.getId().equals(MediumButton.getId())) {
            MediumButton.setStyle("-fx-background-color: #5f808a; ");
            DifficultButton.setStyle("-fx-background-color:  #458788; ");
            EasyButton.setStyle("-fx-background-color:  #458788; ");
            gameType = 1;
        } else {
            EasyButton.setStyle("-fx-background-color:  #5f808a; ");
            DifficultButton.setStyle("-fx-background-color:  #458788; ");
            MediumButton.setStyle("-fx-background-color:  #458788; ");
            gameType = 0;
        }
       StartButton.setDisable(false);
    }
    @FXML
    public void startGame(ActionEvent event) throws IOException {
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
