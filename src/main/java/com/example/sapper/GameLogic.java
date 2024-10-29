package com.example.sapper;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public interface GameLogic {
    void start(Stage primaryStage, int rows, int cols, int mines);
    void revealCell(int row, int col, Label label, GridPane gridPane, Stage primaryStage);
    void toggleFlag(int row, int col, Label label);
    void resetGame(Stage primaryStage);
}