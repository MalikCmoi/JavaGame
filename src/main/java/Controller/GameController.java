package Controller;

import View.GameBoardView;
import Model.Player;

import java.util.HashMap;
import java.util.Map;

public class GameController {
    private GameBoardView gameBoardView;
    private Map<Integer, Player> players = new HashMap<>(); // Map des joueurs avec leurs playerId

    public GameController(GameBoardView gameBoardView) {
        this.gameBoardView = gameBoardView;
    }

    public void addPlayer(int playerId, Player player) {
        players.put(playerId, player);
        gameBoardView.addPlayer(player.getRow(), player.getCol(), playerId);
    }

    // Déplacer un joueur spécifique
    public void movePlayer(int playerId, int newRow, int newCol) {
        Player player = players.get(playerId);

        if (player == null) {
            System.out.println("Joueur avec l'ID " + playerId + " introuvable.");
            return;
        }

        // Simulation d'une validation serveur (remplacez par une vraie validation)
        boolean isValidMove = validateMoveWithServer(playerId, newRow, newCol);

        if (isValidMove) {
            // Mise à jour de la position dans le modèle
            player.setPosition(newRow, newCol);

            // Mise à jour de la vue
            gameBoardView.movePlayer(player.getRow(), player.getCol(), newRow, newCol, playerId);
        } else {
            System.out.println("Déplacement refusé : collision ou règle non respectée.");
        }
    }

    // Méthode fictive pour valider un mouvement avec le serveur
    private boolean validateMoveWithServer(int playerId, int newRow, int newCol) {
        // Simulation d'un appel réseau (toujours "valide" pour l'instant)
        return true;
    }
}