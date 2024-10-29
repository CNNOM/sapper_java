package com.example.sapper;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard {
    public static void initialize(Stage primaryStage, int rows, int cols, int mines) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Label label = new Label();
                label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 1px; -fx-border-color: black; -fx-border-width: 1px; -fx-alignment: center; -fx-background-color: #C0C0C0;");
                label.setMinSize(35, 35);
                label.setPrefSize(35, 35);
                label.setMaxSize(35, 35);

                final int row = i;
                final int col = j;

                label.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        Game.revealCell(row, col, label, gridPane, primaryStage);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        Game.toggleFlag(row, col, label);
                    }
                });

                gridPane.add(label, j, i);
            }
        }

        Scene scene = new Scene(gridPane, cols * 35, rows * 35);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}