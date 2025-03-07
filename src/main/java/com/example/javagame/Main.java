package com.example.javagame;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {

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

    public static void main(String[] args) {
        ApiClient client = new ApiClient("http://localhost:8080/javagame-1.0-SNAPSHOT");

        try {
            // 1) CONNECT
            String responseConnect = client.connect("Alice");
            System.out.println("CONNECT response: " + responseConnect);

            // On récupère l'ID renvoyé par l'API
            int connectedPlayerId = extractPlayerId(responseConnect);
            System.out.println("Le playerId reçu = " + connectedPlayerId);

            // 2) GET GAME-STATE
            String responseGameState = client.getGameState();
            System.out.println("GAME-STATE response: " + responseGameState);

            // 3) MOVE (utilisation de l'ID récupéré)
            String responseMove = client.move(connectedPlayerId, new int[]{2, 3});
            System.out.println("MOVE response: " + responseMove);

            // 4) ATTACK (exemple, à adapter si vous avez un autre joueur)
            // Ici on suppose l'autre joueur a l'ID 456, c'est juste un exemple
            String responseAttack = client.attack(connectedPlayerId, 456);
            System.out.println("ATTACK response: " + responseAttack);

            // 5) END-TURN (avec l'ID du joueur connecté)
            String responseEndTurn = client.endTurn(connectedPlayerId);
            System.out.println("END-TURN response: " + responseEndTurn);

            // 6) GET PLAYERS
            String responsePlayers = client.getPlayers();
            System.out.println("PLAYERS response: " + responsePlayers);

            // 7) CHAT (avec l'ID du joueur connecté)
            String responseChat = client.chat(connectedPlayerId, "Hello, world!");
            System.out.println("CHAT response: " + responseChat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
