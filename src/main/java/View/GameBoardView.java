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

    // Ajout d'un joueur à la grille (ou tout autre élément)
    public void addPlayer(int row, int col) {
        javafx.scene.shape.Circle player = new javafx.scene.shape.Circle(cellSize / 2.5); // Rayon ajusté
        player.setFill(Color.BLUE);

        GridPane.setRowIndex(player, row);
        GridPane.setColumnIndex(player, col);

        grid.getChildren().add(player);
    }

    // Getter pour accéder à la grille
    public GridPane getGameBoard() {
        return grid;
    }
}