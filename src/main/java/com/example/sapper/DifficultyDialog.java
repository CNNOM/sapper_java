package com.example.sapper;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class DifficultyDialog {
    public static void show(Stage primaryStage) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Выбор сложности");

        ButtonType beginner = new ButtonType("Новичок");
        ButtonType amateur = new ButtonType("Любитель");
        ButtonType professional = new ButtonType("Профессионал");
        ButtonType custom = new ButtonType("Пользовательский");

        dialog.getDialogPane().getButtonTypes().addAll(beginner, amateur, professional, custom, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == beginner) {
                Game.start(primaryStage, 8, 8, 10);
            } else if (dialogButton == amateur) {
                Game.start(primaryStage, 16, 16, 40);
            } else if (dialogButton == professional) {
                Game.start(primaryStage, 16, 30, 99);
            } else if (dialogButton == custom) {
                CustomDialog.show(primaryStage);
            }
            return dialogButton;
        });

        dialog.showAndWait();
    }
}