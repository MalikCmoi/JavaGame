package com.example.javagame;

import View.GameBoardView;
import Controller.GameController;
import Model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoardApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Crée une instance du Plateau (Vue)
        GameBoardView gameBoardView = new GameBoardView();

        Player player = new Player(0, 0, 100, 0);

        GameController controller = new GameController(gameBoardView, player);
        controller.spawnPlayer();

        Scene scene = new Scene(gameBoardView.getGameBoard(), 800, 600);
        stage.setTitle("Battle Arena - Jeu Multijoueur");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}