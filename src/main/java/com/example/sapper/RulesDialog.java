package com.example.sapper;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ButtonBar.ButtonData;

public class RulesDialog {
    public void show(Stage primaryStage, GameLogic gameLogic) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Правила игры");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("Правила игры в сапёр:"),
                new Label("1. Цель игры — открыть все клетки, не содержащие мины."),
                new Label("2. Если вы откроете клетку с миной, игра закончится."),
                new Label("3. Если вы откроете клетку с числом, оно указывает на количество мин вокруг этой клетки."),
                new Label("4. Вы можете пометить клетку флагом, чтобы отметить её как потенциально содержащую мину."),
                new Label("5. Игра заканчивается победой, если вы откроете все клетки, не содержащие мины.")
        );

        dialog.getDialogPane().setContent(vbox);

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                return okButton;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                DifficultyDialog difficultyDialog = new DifficultyDialog();
                difficultyDialog.show(primaryStage, gameLogic);
            }
        });
    }
}