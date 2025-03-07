package com.example.javagame;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080/";
    private final HttpClient httpClient;

    public ApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }
    //POST /connect
    public String connect(String playerName) throws IOException, InterruptedException {
        // Créer le corps JSON pour la requête POST
        String requestBody = "{\"playerName\":\"" + playerName + "\"}";

        // Créer la requête HTTP avec la méthode POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/connect"))  // URL de la requête
                .header("Content-Type", "application/json")  // Définir le type de contenu
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))  // Corps de la requête
                .build();

        // Envoyer la requête et obtenir la réponse
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Retourner le corps de la réponse (réponse du serveur)
        return response.body();
    }
    //GET /game-state
    public String getGameState() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/game-state"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne l'état du jeu (JSON)
    }

    //post /Move
    public String move(String playerId, String newPosition) throws IOException, InterruptedException {
        String requestBody = "{\"playerId\":\"" + playerId + "\", \"newPosition\":\"" + newPosition + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/move"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne la réponse avec la nouvelle position ou une erreur
    }
    // POST /attack
    public String attack(String playerId, String targetId) throws IOException, InterruptedException {
        String requestBody = "{\"playerId\":\"" + playerId + "\", \"targetId\":\"" + targetId + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/attack"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne les détails de l'attaque, XP gagné, et HP restant du target
    }
    // GET /players
    public String getPlayers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/players"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne la liste des joueurs
    }
    // POST /chat
    public String sendChatMessage(String playerId, String message) throws IOException, InterruptedException {
        String requestBody = "{\"playerId\":\"" + playerId + "\", \"message\":\"" + message + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/chat"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne la réponse du serveur (par exemple, le message envoyé)
    }
    // POST /end-turn
    public String endTurn(String playerId) throws IOException, InterruptedException {
        String requestBody = "{\"playerId\":\"" + playerId + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/end-turn"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Retourne la réponse avec le joueur suivant (nextPlayer)
    }
}
