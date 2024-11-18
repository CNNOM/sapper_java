package com.example.sapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyDialog {

    @FXML
    private HBox difficultyDialog;

    @FXML
    private Button beginner;

    @FXML
    private Button amateur;

    @FXML
    private Button professional;

    @FXML
    private Button custom;

    private GameLogic gameLogic;
    private Stage primaryStage;

    public void show(Stage primaryStage, GameLogic gameLogic) {
        this.primaryStage = primaryStage;
        this.gameLogic = gameLogic;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DifficultyDialog.fxml"));
            loader.setController(this);
            HBox dialogHBox = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Выбор сложности");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialogHBox);
            dialogStage.setScene(scene);

            dialogStage.setWidth(700);
            dialogStage.setHeight(200);

            beginner.setOnAction(event -> {
                gameLogic.start(primaryStage, 8, 8, 10);
                dialogStage.close();
            });

            amateur.setOnAction(event -> {
                gameLogic.start(primaryStage, 16, 16, 40);
                dialogStage.close();
            });

            professional.setOnAction(event -> {
                gameLogic.start(primaryStage, 16, 30, 99);
                dialogStage.close();
            });

            custom.setOnAction(event -> {
                CustomDialog customDialog = new CustomDialog();
                customDialog.show(primaryStage, gameLogic);
                dialogStage.close();
            });

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}