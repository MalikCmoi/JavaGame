package View;

import Controller.GameController;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class GameBoardView {
    private final int rows = 10;             // Lignes du plateau
    private final int cols = 10;             // Colonnes du plateau
    private final int cellSize = 50;         // Taille des cellules
    private GridPane grid;
    private GameController controller;       // Contrôleur relié à la vue

    // Map pour suivre les icônes des joueurs
    private Map<Integer, Circle> playerIcons = new HashMap<>();

    public GameBoardView() {
        grid = new GridPane();
        createBoard();  // Initialise la grille
    }

    // Connecte la vue au contrôleur (via GameBoardApplication)
    public void setController(GameController controller) {
        this.controller = controller;
    }

    // Création de la grille
    private void createBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Crée une cellule
                Rectangle cell = new Rectangle(cellSize, cellSize);
                cell.setFill(Color.BEIGE);
                cell.setStroke(Color.BROWN);

                // Ajoute un clic événementiel pour tenter un déplacement
                final int finalRow = row;
                final int finalCol = col;
                cell.setOnMouseClicked(event -> handleCellClick(finalRow, finalCol));

                // Ajoute la cellule à la grille
                grid.add(cell, col, row);
            }
        }
    }

    // Gestion du clic pour déplacer un joueur
    private void handleCellClick(int row, int col) {
        if (controller == null) {
            System.out.println("Erreur : Contrôleur non connecté !");
            return;
        }

        // Demander au contrôleur de gérer le déplacement du joueur actif
        controller.movePlayer(controller.getCurrentPlayerId(), row, col);
    }

    // Ajout d'un joueur (visuellement)
    public void addPlayer(int row, int col, int playerId) {
        Circle player = new Circle(cellSize / 2.5); // Rayon ajusté

        // Différencier les couleurs des joueurs
        if (playerId == 1) player.setFill(Color.BLUE);
        else if (playerId == 2) player.setFill(Color.RED);
        else player.setFill(Color.GREEN);

        // Ajouter l'icône du joueur
        playerIcons.put(playerId, player);
        GridPane.setRowIndex(player, row);
        GridPane.setColumnIndex(player, col);
        grid.getChildren().add(player);
    }

    // Déplacer un joueur (visuellement)
    public void movePlayer(int oldRow, int oldCol, int newRow, int newCol, int playerId) {
        Circle player = playerIcons.get(playerId);

        if (player == null) {
            System.out.println("Erreur : Joueur " + playerId + " introuvable dans la vue !");
            return;
        }

        // Mettre à jour les positions du joueur
        GridPane.setRowIndex(player, newRow);
        GridPane.setColumnIndex(player, newCol);

        System.out.println("Vue mise à jour : Joueur " + playerId + " déplacé vers (" + newRow + ", " + newCol + ")");
    }

    // Obtenir la grille
    public GridPane getGameBoard() {
        return grid;
    }
}