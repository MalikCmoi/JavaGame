package com.example.javagame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de ApiClient
        ApiClient apiClient = new ApiClient();

        // Tester la connexion avec un nom de joueur (par exemple, "JohnDoe")
        try {
            String response = apiClient.connect("JohnDoe");
            System.out.println("Réponse de la connexion : " + response);

            // Récupérer l'état du jeu
            String gameState = apiClient.getGameState();
            System.out.println("État du jeu : " + gameState);

            // Simuler un déplacement
            String moveResponse = apiClient.move("123", "newPosition");
            System.out.println("Réponse du déplacement : " + moveResponse);

            // Simuler une attaque
            String attackResponse = apiClient.attack("123", "456");
            System.out.println("Réponse de l'attaque : " + attackResponse);

            // Récupérer la liste des joueurs
            String playersResponse = apiClient.getPlayers();
            System.out.println("Liste des joueurs : " + playersResponse);

            // Envoyer un message de chat
            String chatResponse = apiClient.sendChatMessage("123", "Salut tout le monde !");
            System.out.println("Réponse du chat : " + chatResponse);

            // Terminer le tour
            String endTurnResponse = apiClient.endTurn("123");
            System.out.println("Réponse de la fin de tour : " + endTurnResponse);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}