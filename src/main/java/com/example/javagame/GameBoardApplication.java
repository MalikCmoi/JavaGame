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
        // Création de la vue du plateau de jeu
        GameBoardView gameBoardView = new GameBoardView();

        // Création des joueurs
        Player player1 = new Player(0, 0, 100, 0);  // Joueur 1 position initiale
        Player player2 = new Player(9, 9, 100, 0);  // Joueur 2 position initiale

        // Création du contrôleur qui gère plusieurs joueurs
        GameController controller = new GameController(gameBoardView);

        // Ajout des deux joueurs au contrôleur
        controller.addPlayer(1, player1);
        controller.addPlayer(2, player2);

        // Création de la scène
        Scene scene = new Scene(gameBoardView.getGameBoard(), 800, 600);
        stage.setTitle("Battle Arena - Jeu Multijoueur");
        stage.setScene(scene);
        stage.show();

        // Simuler un déplacement (par exemple après un délai)
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Pause de 2 secondes
                controller.movePlayer(1, 1, 1);  // Déplace le joueur 1 à (1, 1)
                Thread.sleep(2000); // Pause encore
                controller.movePlayer(2, 8, 8);  // Déplace le joueur 2 à (8, 8)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}