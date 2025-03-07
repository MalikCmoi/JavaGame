package Controller;

import View.GameBoardView;
import Model.Player;

public class GameController {
    private GameBoardView gameBoardView;
    private Player player;
    public GameController(GameBoardView gameBoardView, Player player) {
        this.gameBoardView = gameBoardView;
        this.player = player;
    }

    public void spawnPlayer() {
        gameBoardView.addPlayer(player.getRow(), player.getCol());
    }

    public void movePlayer(int newRow, int newCol) {
        player.setPosition(newRow, newCol); // Mise à jour de la position dans le modèle
        gameBoardView.addPlayer(newRow, newCol); // Ajout d'une boule à la nouvelle position
    }
    }