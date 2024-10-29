package com.example.sapper;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        DifficultyDialog.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}