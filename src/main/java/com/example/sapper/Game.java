package com.example.sapper;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Game {
    private static int ROWS;
    private static int COLS;
    private static int MINES;
    private static String[][] field;
    private static boolean[][] revealed;
    private static boolean[][] flagged;
    private static boolean firstClick = true;
    private static int firstClickRow = -1;
    private static int firstClickCol = -1;

    public static void start(Stage primaryStage, int rows, int cols, int mines) {
        ROWS = rows;
        COLS = cols;
        MINES = mines;
        revealed = new boolean[ROWS][COLS];
        flagged = new boolean[ROWS][COLS];
        GameBoard.initialize(primaryStage, rows, cols, mines);
    }

    public static void revealCell(int row, int col, Label label, GridPane gridPane, Stage primaryStage) {
        if (firstClick) {
            firstClickRow = row;
            firstClickCol = col;
            generateField();
            firstClick = false;
        }

        if (revealed[row][col] || flagged[row][col]) {
            return;
        }

        revealed[row][col] = true;
        label.setText(field[row][col]);

        if (field[row][col].equals("*")) {
            label.setStyle(label.getStyle() + " -fx-background-color: red;");
            showGameOverDialog(primaryStage);
        } else if (field[row][col].equals("0")) {
            for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, ROWS - 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, COLS - 1); j++) {
                    Label adjacentLabel = (Label) getNodeByRowColumnIndex(i, j, gridPane);
                    if (adjacentLabel != null) {
                        revealCell(i, j, adjacentLabel, gridPane, primaryStage);
                        label.setStyle(label.getStyle() + "-fx-background-color: white;");
                    }
                }
            }
        } else {
            int num = Integer.parseInt(field[row][col]);
            String colorStyle = getColorStyle(num);
            label.setStyle(label.getStyle() + " -fx-text-fill: " + colorStyle + "; -fx-background-color: white;");
        }

        if (checkWinCondition()) {
            showWinDialog(primaryStage);
        }
    }

    public static void toggleFlag(int row, int col, Label label) {
        if (revealed[row][col]) {
            return;
        }

        flagged[row][col] = !flagged[row][col];

        if (flagged[row][col]) {
            Group group = new Group();

            group.setScaleX(25.0 / 512.0); // 512 - это размер исходного SVG
            group.setScaleY(25.0 / 512.0);

            // Polygon 1
            Polygon polygon1 = new Polygon();
            polygon1.getPoints().addAll(
                    54.557, 75.541,
                    491.016, 75.541,
                    440.656, 218.23,
                    491.016, 360.918,
                    54.557, 360.918
            );
            polygon1.setFill(Color.rgb(255, 0, 48));
            group.getChildren().add(polygon1);

            // Rectangle
            Rectangle rectangle = new Rectangle(37.77, 75.541, 235.016, 285.377);
            rectangle.setFill(Color.rgb(255, 0, 48));
            group.getChildren().add(rectangle);

            // SVGPath
            SVGPath svgPath = new SVGPath();
            svgPath.setContent("M104.918,41.967C104.918,18.79 86.128,0 62.951,0S20.984,18.79 20.984,41.967C20.984,55.697 27.578,67.884 37.77,75.541V512h50.361V75.541C88.324,67.884 94.918,55.697 94.918,41.967z");
            svgPath.setFill(Color.rgb(0, 0, 0));
            group.getChildren().add(svgPath);

            // Polygon 2
            Polygon polygon2 = new Polygon();
            polygon2.getPoints().addAll(
                    356.721, 184.656,
                    306.361, 184.656,
                    306.361, 134.295,
                    239.213, 134.295,
                    239.213, 184.656,
                    188.852, 184.656,
                    188.852, 251.803,
                    239.213, 251.803,
                    239.213, 302.164,
                    306.361, 302.164,
                    306.361, 251.803,
                    356.721, 251.803
            );
            polygon2.setFill(Color.rgb(255, 0, 48));
            group.getChildren().add(polygon2);

            // Polygon 3
            Polygon polygon3 = new Polygon();
            polygon3.getPoints().addAll(
                    306.361, 184.656,
                    306.361, 134.295,
                    272.787, 134.295,
                    272.787, 302.164,
                    306.361, 302.164,
                    306.361, 251.803,
                    356.721, 251.803,
                    356.721, 184.656
            );
            polygon3.setFill(Color.rgb(255, 0, 48));
            group.getChildren().add(polygon3);

            label.setGraphic(group);
        } else {
            label.setGraphic(null);
        }
    }

    private static void generateField() {
        field = new String[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                field[i][j] = "0";
            }
        }

        int minesPlaced = 0;
        while (minesPlaced < MINES) {
            int row = (int) (Math.random() * ROWS);
            int col = (int) (Math.random() * COLS);
            if ((row != firstClickRow || col != firstClickCol) &&
                    (row < firstClickRow - 1 || row > firstClickRow + 1 || col < firstClickCol - 1 || col > firstClickCol + 1)) {
                if (!field[row][col].equals("*")) {
                    field[row][col] = "*";
                    minesPlaced++;
                }
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!field[i][j].equals("*")) {
                    int mineCount = 0;
                    for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, ROWS - 1); x++) {
                        for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, COLS - 1); y++) {
                            if (field[x][y].equals("*")) {
                                mineCount++;
                            }
                        }
                    }
                    field[i][j] = String.valueOf(mineCount);
                }
            }
        }
    }

    private static boolean checkWinCondition() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!field[i][j].equals("*") && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void showWinDialog(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Победа!");
        alert.setHeaderText("Вы выиграли!");
        alert.setContentText("Вы открыли все поля, кроме мин.");

        ButtonType restartButton = new ButtonType("Начать с начала", ButtonData.YES);
        ButtonType mainMenuButton = new ButtonType("Главное меню", ButtonData.NO);

        alert.getButtonTypes().setAll(restartButton, mainMenuButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == restartButton) {
                resetGame(primaryStage);
            } else if (response == mainMenuButton) {
                DifficultyDialog.show(primaryStage);
            }
        });
    }

    private static void showGameOverDialog(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Вы проиграли!");
        alert.setContentText("Вы наткнулись на мину.");

        ButtonType restartButton = new ButtonType("Начать с начала", ButtonData.YES);
        ButtonType mainMenuButton = new ButtonType("Главное меню", ButtonData.NO);

        alert.getButtonTypes().setAll(restartButton, mainMenuButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == restartButton) {
                resetGame(primaryStage);
            } else if (response == mainMenuButton) {
                DifficultyDialog.show(primaryStage);
            }
        });
    }

    private static void resetGame(Stage primaryStage) {
        firstClick = true;
        firstClickRow = -1;
        firstClickCol = -1;
        revealed = new boolean[ROWS][COLS];
        flagged = new boolean[ROWS][COLS];
        GameBoard.initialize(primaryStage, ROWS, COLS, MINES);
    }

    private static String getColorStyle(int num) {
        switch (num) {
            case 1: return "blue";
            case 2: return "green";
            case 3: return "red";
            case 4: return "darkblue";
            case 5: return "brown";
            case 6: return "cyan";
            case 7: return "black";
            case 8: return "gray";
            default: return "white";
        }
    }

    private static javafx.scene.Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }
}