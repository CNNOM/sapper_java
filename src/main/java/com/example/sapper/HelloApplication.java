package com.example.sapper;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private GameLogic gameLogic;

    public HelloApplication() {
        this.gameLogic = new Game();
    }

    @Override
    public void start(Stage stage) {
        RulesDialog rulesDialog = new RulesDialog();
        rulesDialog.show(stage, gameLogic);
    }

    public static void main(String[] args) {
        launch(args);
    }
}