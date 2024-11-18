package com.example.sapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RulesDialog {

    @FXML
    private VBox rulesDialog;

    @FXML
    private Button okButton;

    private GameLogic gameLogic;
    private Stage primaryStage;

    public void show(Stage primaryStage, GameLogic gameLogic) {
        this.primaryStage = primaryStage;
        this.gameLogic = gameLogic;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesDialog.fxml"));
            loader.setController(this);
            VBox dialogVBox = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Правила игры");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialogVBox);
            dialogStage.setScene(scene);

            okButton.setOnAction(event -> {
                okButton.setVisible(false);

                dialogStage.setX(50);
                dialogStage.setY(50);

                DifficultyDialog difficultyDialog = new DifficultyDialog();
                difficultyDialog.show(primaryStage, gameLogic);
            });

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}