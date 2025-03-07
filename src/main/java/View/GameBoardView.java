package View;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class GameBoardView {
    private final int rows = 10;              // Lignes du plateau
    private final int cols = 10;              // Colonnes du plateau
    private final int cellSize = 50;          // Taille des cellules
    private GridPane grid;

    public GameBoardView() {
        grid = new GridPane();
        createBoard();  // Création initiale de la grille.
    }

    // Méthode pour créer la grille
    private void createBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle cell = new Rectangle(cellSize, cellSize);
                cell.setFill(Color.LIGHTGRAY); // Couleur de remplissage
                cell.setStroke(Color.BLACK);  // Bordure
                grid.add(cell, col, row);
            }
        }
    }

    public void addObstacle(int row, int col) {
        Rectangle obstacle = new Rectangle(cellSize, cellSize);
        obstacle.setFill(Color.DARKGRAY); // Couleur de l'obstacle
        obstacle.setStroke(Color.BLACK); // Bordure de l'obstacle

        GridPane.setRowIndex(obstacle, row);
        GridPane.setColumnIndex(obstacle, col);

        grid.getChildren().add(obstacle);
    }

// Ajout d'un joueur identifié
    public void addPlayer(int row, int col, int playerId) {
        javafx.scene.shape.Circle player = new javafx.scene.shape.Circle(cellSize / 2.5); // Rayon ajusté
        if (playerId == 1) {
            player.setFill(Color.BLUE);
        } else if (playerId == 2) {
            player.setFill(Color.RED);
        }

        GridPane.setRowIndex(player, row);
        GridPane.setColumnIndex(player, col);

        grid.getChildren().add(player);
    }

    // Déplace un joueur après validation
    public void movePlayer(int oldRow, int oldCol, int newRow, int newCol, int playerId) {
        // Supprime l'ancien joueur
        grid.getChildren().removeIf(node ->
                GridPane.getRowIndex(node) == oldRow &&
                        GridPane.getColumnIndex(node) == oldCol &&
                        node instanceof javafx.scene.shape.Circle);

        // Ajoute le joueur à la nouvelle position
        addPlayer(newRow, newCol, playerId);
    }

    public GridPane getGameBoard() {
        return grid;
    }
}