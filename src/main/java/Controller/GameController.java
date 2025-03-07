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

    public void spawnPlayer(int playerId) {
        gameBoardView.addPlayer(player.getRow(), player.getCol(), playerId);
    }

    public void movePlayer(int playerId, int newRow, int newCol) {
        // Simulation d'une validation serveur (remplacer par une vraie validation côté serveur)
        boolean isValidMove = validateMoveWithServer(playerId, newRow, newCol);

        if (isValidMove) {
            // Mise à jour de la position dans le modèle
            player.setPosition(newRow, newCol);

            // Mise à jour de l'affichage
            gameBoardView.movePlayer(player.getRow(), player.getCol(), newRow, newCol, playerId);
        } else {
            System.out.println("Déplacement refusé : collision ou règle non respectée.");
        }
    }

    // Méthode fictive pour valider un mouvement avec le serveur
    private boolean validateMoveWithServer(int playerId, int newRow, int newCol) {
        // Appel à une API ou socket réseau simulée
        // Retourne "true" si le serveur valide, sinon "false"
        return true; // Remplacez ceci par la logique réelle du serveur
    }
    }