package com.example.sapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomDialog {

    @FXML
    private TextField rowsField;

    @FXML
    private TextField colsField;

    @FXML
    private TextField minesField;

    @FXML
    private Button okButton;

    private GameLogic gameLogic;
    private Stage primaryStage;

    public void show(Stage primaryStage, GameLogic gameLogic) {
        this.primaryStage = primaryStage;
        this.gameLogic = gameLogic;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomDialog.fxml"));
            loader.setController(this);
            VBox dialogVBox = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Пользовательский");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialogVBox);
            dialogStage.setScene(scene);

            okButton.setOnAction(event -> {
                try {
                    int rows = Integer.parseInt(rowsField.getText());
                    int cols = Integer.parseInt(colsField.getText());
                    int mines = Integer.parseInt(minesField.getText());
                    gameLogic.start(primaryStage, rows, cols, mines);
                    dialogStage.close();
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Пожалуйста, введите корректные числа.");
                    alert.showAndWait();
                }
            });

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}