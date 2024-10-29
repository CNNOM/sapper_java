package com.example.sapper;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomDialog {
    public void show(Stage primaryStage, GameLogic gameLogic) {
        Dialog<ButtonType> customDialog = new Dialog<>();
        customDialog.setTitle("Пользовательский");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField rowsField = new TextField();
        rowsField.setPromptText("Строки");
        TextField colsField = new TextField();
        colsField.setPromptText("Столбцы");
        TextField minesField = new TextField();
        minesField.setPromptText("Мины");

        grid.add(new Label("Строки:"), 0, 0);
        grid.add(rowsField, 1, 0);
        grid.add(new Label("Столбцы:"), 0, 1);
        grid.add(colsField, 1, 1);
        grid.add(new Label("Мины:"), 0, 2);
        grid.add(minesField, 1, 2);

        customDialog.getDialogPane().setContent(grid);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        customDialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        customDialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                try {
                    int rows = Integer.parseInt(rowsField.getText());
                    int cols = Integer.parseInt(colsField.getText());
                    int mines = Integer.parseInt(minesField.getText());
                    gameLogic.start(primaryStage, rows, cols, mines);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Пожалуйста, введите корректные числа.");
                    alert.showAndWait();
                }
            }
            return dialogButton;
        });

        customDialog.showAndWait();
    }
}