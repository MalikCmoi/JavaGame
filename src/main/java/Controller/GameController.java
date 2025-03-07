package Controller;

import View.GameBoardView;
import Model.Player;

import java.util.HashMap;
import java.util.Map;

public class GameController {
    private GameBoardView gameBoardView;
    private int currentPlayerId = 1;
    private Map<Integer, Player> players = new HashMap<>(); // Map des joueurs avec leurs playerId

    public GameController(GameBoardView gameBoardView) {
        this.gameBoardView = gameBoardView;
    }

    /**
     * Ajoute un joueur à la partie.
     * @param playerId Identifiant unique du joueur.
     * @param player Objet Player contenant les informations du joueur.
     */
    public void addPlayer(int playerId, Player player) {
        players.put(playerId, player); // Mémoriser le joueur
        gameBoardView.addPlayer(player.getRow(), player.getCol(), playerId); // Ajouter visuellement
        System.out.println("Joueur ajouté : ID=" + playerId + ", Position=(" + player.getRow() + "," + player.getCol() + ")");
    }

    /**
     * Déplacer un joueur dans une nouvelle position.
     * @param playerId Identifiant du joueur à déplacer.
     * @param newRow Ligne cible.
     * @param newCol Colonne cible.
     */
    public void movePlayer(int playerId, int newRow, int newCol) {
        Player player = players.get(playerId); // Récupérer le joueur

        // Vérifier si le joueur existe
        if (player == null) {
            System.out.println("Joueur avec l'ID " + playerId + " introuvable.");
            return; // On arrête si le joueur n'existe pas
        }

        // Validation auprès du serveur
        boolean isValidMove = validateMoveWithServer(playerId, newRow, newCol);

        if (isValidMove) {
            // Sauvegarder la position précédente
            int oldRow = player.getRow();
            int oldCol = player.getCol();

            // Mettre à jour la position dans le modèle (backend)
            player.setPosition(newRow, newCol);

            // Mettre à jour la position dans la vue (frontend)
            gameBoardView.movePlayer(oldRow, oldCol, newRow, newCol, playerId);

            System.out.println("Déplacement réussi pour le joueur " + playerId + " : De (" + oldRow + "," + oldCol + ") à (" + newRow + "," + newCol + ")");
        } else {
            // Si le déplacement est refusé
            System.out.println("Déplacement refusé pour le joueur " + playerId + " vers (" + newRow + "," + newCol + ")");
        }
    }

    /**
     * Valider un mouvement auprès du serveur.
     * Méthode simulée pour le moment. Peut être remplacée par un appel réel à une API REST.
     * @param playerId Identifiant du joueur.
     * @param newRow Ligne cible.
     * @param newCol Colonne cible.
     * @return true si le mouvement est valide, false sinon.
     */

    private boolean validateMoveWithServer(int playerId, int newRow, int newCol) {
        System.out.println("Validation serveur : tous les mouvements sont refusés.");
        return false;
    }

    // Retourne l'identifiant du joueur actuellement actif
    public int getCurrentPlayerId() {
        return currentPlayerId;
    }
}