package View;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class GameBoardView {
    private final int rows = 10;              // Lignes du plateau
    private final int cols = 10;              // Colonnes du plateau
    private final int cellSize = 50;          // Taille des cellules
    private GridPane grid;

    // Map pour suivre les joueurs actuellement sur la grille
    private Map<Integer, Circle> playerIcons = new HashMap<>();

    public GameBoardView() {
        grid = new GridPane();
        createBoard();  // Création initiale de la grille.
    }

    // Création de la grille interactive
    private void createBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Crée une cellule
                Rectangle cell = new Rectangle(cellSize, cellSize);
                cell.setFill(Color.BEIGE);
                cell.setStroke(Color.BROWN);

                // Ajoute un événement de clic pour déplacer un joueur
                final int finalRow = row;
                final int finalCol = col;
                cell.setOnMouseClicked(event -> {
                    handleCellClick(finalRow, finalCol); // Gère le déplacement sur clic
                });

                // Ajoute la cellule à la grille
                grid.add(cell, col, row);
            }
        }
    }

    private int currentPlayerId = 1; // ID du joueur actif (alterne entre 1 et 2, etc.)

    // Gérer le déplacement d'un joueur au clic
    private void handleCellClick(int row, int col) {
        // Vérifie qui doit jouer (joueur actif)
        Circle player = playerIcons.get(currentPlayerId);
        if (player == null) {
            System.out.println("Erreur : Joueur introuvable !");
            return;
        }

        // Récupérer la position actuelle du joueur
        Integer oldRow = GridPane.getRowIndex(player);
        Integer oldCol = GridPane.getColumnIndex(player);

        if (oldRow == null || oldCol == null) {
            System.out.println("Erreur : Position actuelle du joueur inconnue !");
            return;
        }

        // Déplacer visuellement le joueur
        movePlayer(oldRow, oldCol, row, col, currentPlayerId);

        // Alterner le joueur actif
        currentPlayerId = (currentPlayerId == 1) ? 2 : 1;
    }

    // Ajout d'un joueur identifié à la grille
    public void addPlayer(int row, int col, int playerId) {
        Circle player = new Circle(cellSize / 2.5); // Rayon ajusté
        if (playerId == 1) {
            player.setFill(Color.BLUE); // Joueur 1
        } else if (playerId == 2) {
            player.setFill(Color.RED); // Joueur 2
        } else {
            player.setFill(Color.GREEN); // Autres joueurs
        }

        playerIcons.put(playerId, player); // Associer l'identifiant à l'icône graphique du joueur
        GridPane.setRowIndex(player, row);
        GridPane.setColumnIndex(player, col);
        grid.getChildren().add(player);
    }

    // Déplacer un joueur existant
    public void movePlayer(int oldRow, int oldCol, int newRow, int newCol, int playerId) {
        // Récupérer l'icône du joueur via son ID
        Circle player = playerIcons.get(playerId);

        if (player == null) {
            System.out.println("Erreur : Joueur avec l'ID " + playerId + " non trouvé !");
            return;
        }

        // Vérifie si le joueur existe déjà dans la liste des enfants (au cas où il aurait été supprimé)
        if (!grid.getChildren().contains(player)) {
            System.out.println("Erreur : L'icône du joueur est manquante sur le plateau.");
            return;
        }

        // Supprime l'ancienne position (elle sera automatiquement gérée dans JavaFX)
        grid.getChildren().remove(player);

        // Met à jour les positions dans le GridPane (nouvel emplacement)
        GridPane.setRowIndex(player, newRow);
        GridPane.setColumnIndex(player, newCol);

        // Réajoute à la grille si nécessaire pour éviter tout manque
        grid.getChildren().add(player);
    }

    // Getter pour accéder à la grille
    public GridPane getGameBoard() {
        return grid;
    }
}