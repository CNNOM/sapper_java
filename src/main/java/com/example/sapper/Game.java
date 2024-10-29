package com.example.sapper;

import javafx.stage.Stage;

public class Game extends AbstractGame {
    @Override
    public void start(Stage primaryStage, int rows, int cols, int mines) {
        super.start(primaryStage, rows, cols, mines);
        GameBoard.initialize(primaryStage, rows, cols, mines, this);
    }

    @Override
    public void resetGame(Stage primaryStage) {
        super.resetGame(primaryStage);
        GameBoard.initialize(primaryStage, ROWS, COLS, MINES, this);
    }
}