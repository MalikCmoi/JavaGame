package com.example.javagame;

import View.GameBoardView;
import Controller.GameController;
import Model.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameBoardApplication extends Application {

    private GameController controller;
    List<Player> playersList = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
        // Création de la vue du plateau de jeu
        GameBoardView gameBoardView = new GameBoardView();

        ApiClient apiClient = new ApiClient("http://localhost:8080/javagame-1.0-SNAPSHOT");

        int myId = extractPlayerId(apiClient.connect("ZZEZE"));

        // Création des joueurs
        Player player1 = new Player(0, 0, 100, 0);
        playersList.add(player1);
        controller = new GameController(gameBoardView);

        // Ajout des deux joueurs au contrôleur
        controller.addPlayer(myId, player1);

        // Création de la scène
        Scene scene = new Scene(gameBoardView.getGameBoard(), 800, 600);
        stage.setTitle("Battle Arena - Jeu Multijoueur");
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println();
                    parsePlayers(apiClient.getPlayers());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }

    public void parsePlayers(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode playersNode = root.get("players");


        for (JsonNode node : playersNode) {
            int id = node.get("id").asInt();
            String name = node.get("name").asText();
            int hp = node.get("hp").asInt();
            JsonNode posNode = node.get("position");
            int[] position = new int[]{ posNode.get(0).asInt(), posNode.get(1).asInt() };

            // Créer un nouveau joueur et affecter les propriétés
            Player newPlayer = new Player(position[0], position[1], hp, 0);
            newPlayer.id = id;

            // Vérifier si un joueur avec cet id existe déjà dans playersList
            boolean exists = false;

            for (Player player : playersList) {
                if (player.id == id) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                playersList.add(newPlayer);

                Platform.runLater(() -> controller.addPlayer(id, newPlayer));
            }else{
                System.out.println("Player already exists");
                controller.movePlayer(id,position[0], position[1]);
            }
        }
    }

    private static int extractPlayerId(String json) {
        int idx = json.indexOf("\"playerId\":");
        if (idx == -1) {
            return -1; // non trouvé
        }
        // on cherche le ":" puis la prochaine virgule ou accolade fermante
        int start = json.indexOf(":", idx) + 1;
        int commaIndex = json.indexOf(",", start);
        int braceIndex = json.indexOf("}", start);

        int end;
        if (commaIndex == -1 && braceIndex == -1) {
            end = json.length();
        } else if (commaIndex == -1) {
            end = braceIndex;
        } else if (braceIndex == -1) {
            end = commaIndex;
        } else {
            end = Math.min(commaIndex, braceIndex);
        }

        String idValue = json.substring(start, end).trim();
        return Integer.parseInt(idValue);
    }
}