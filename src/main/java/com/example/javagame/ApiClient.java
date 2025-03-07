package com.example.javagame;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ApiClient {
    private final String BASE_URL;

    public ApiClient(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    // Méthode utilitaire pour effectuer un POST JSON
    private String doPost(String endpoint, String jsonBody) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Lecture de la réponse
        StringBuilder response = new StringBuilder();
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
            }
        }
        conn.disconnect();
        return response.toString();
    }

    // Méthode utilitaire pour effectuer un GET
    private String doGet(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        conn.disconnect();
        return response.toString();
    }

    // 1) CONNECT
    public String connect(String playerName) throws IOException {
        String body = "{\"playerName\":\"" + playerName + "\"}";
        return doPost("/connect", body);
    }

    // 2) GET GAME-STATE
    public String getGameState() throws IOException {
        return doGet("/game-state");
    }

    // 3) MOVE
    public String move(int playerId, int[] newPosition) throws IOException {
        // Exemple: {"playerId":123,"newPosition":[2,3]}
        String body = "{\"playerId\":" + playerId +
                ",\"newPosition\":" + Arrays.toString(newPosition) + "}";
        System.out.println("body: " + body);
        return doPost("/move", body);
    }

    // 4) ATTACK
    public String attack(int playerId, int targetId) throws IOException {
        // Exemple: {"playerId":123,"targetId":456}
        String body = "{\"playerId\":" + playerId +
                ",\"targetId\":" + targetId + "}";
        return doPost("/attack", body);
    }

    // 5) END-TURN
    public String endTurn(int playerId) throws IOException {
        // Exemple: {"playerId":123}
        String body = "{\"playerId\":" + playerId + "}";
        return doPost("/end-turn", body);
    }

    // 6) GET PLAYERS
    public String getPlayers() throws IOException {
        return doGet("/players");
    }

    // 7) CHAT
    public String chat(int playerId, String message) throws IOException {
        // Exemple: {"playerId":123,"message":"Hello"}
        String body = "{\"playerId\":" + playerId +
                ",\"message\":\"" + message + "\"}";
        return doPost("/chat", body);
    }
}