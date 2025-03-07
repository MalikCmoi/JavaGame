package com.example.javagame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameBoardApplication extends Application {
    private final int rows = 10;
    private final int cols = 10;
    private final int cellSize = 50;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle cell = new Rectangle(cellSize, cellSize);
                cell.setFill(Color.BEIGE);
                cell.setStroke(Color.BROWN);
                grid.add(cell, col, row);
            }
        }

        Scene scene = new Scene(grid, cols * cellSize, rows * cellSize);
        stage.setTitle("Battle Arena - Plateau de Jeu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}