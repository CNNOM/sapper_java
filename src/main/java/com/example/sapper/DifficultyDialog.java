package com.example.sapper;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class DifficultyDialog {
    public void show(Stage primaryStage, GameLogic gameLogic) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Выбор сложности");

        ButtonType beginner = new ButtonType("Новичок");
        ButtonType amateur = new ButtonType("Любитель");
        ButtonType professional = new ButtonType("Профессионал");
        ButtonType custom = new ButtonType("Пользовательский");

        dialog.getDialogPane().getButtonTypes().addAll(beginner, amateur, professional, custom, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == beginner) {
                gameLogic.start(primaryStage, 8, 8, 10);
            } else if (dialogButton == amateur) {
                gameLogic.start(primaryStage, 16, 16, 40);
            } else if (dialogButton == professional) {
                gameLogic.start(primaryStage, 16, 30, 99);
            } else if (dialogButton == custom) {
                CustomDialog customDialog = new CustomDialog();
                customDialog.show(primaryStage, gameLogic);
            }
            return dialogButton;
        });

        dialog.showAndWait();
    }
}