package com.example.javagame;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ApiRestful {
    private static final PlayerRepository repo = new PlayerRepository();

    @POST
    @Path("/connect")
    public Response connect(ConnectRequest req) {
        if (req.getPlayerName() == null || req.getPlayerName().isEmpty()) {
            return Response.status(400).entity("Invalid name").build();
        }
        Player p = repo.connectPlayer(req.getPlayerName());
        List<Player> players = repo.getAllPlayers();
        Map<String,Object> gameStateMap = new HashMap<>();
        gameStateMap.put("players", players);
        gameStateMap.put("turn", repo.getCurrentTurn());
        Map<String,Object> result = new HashMap<>();
        result.put("playerId", p.getId());
        result.put("gameState", gameStateMap);
        return Response.ok(result).build();
    }

    @GET
    @Path("/game-state")
    public Response getGameState() {
        List<Player> players = repo.getAllPlayers();
        GameState gs = new GameState(players, repo.getCurrentTurn());
        return Response.ok(gs).build();
    }

    @POST
    @Path("/move")
    public Response move(MoveRequest req) {
        Player pl = repo.getPlayer(req.getPlayerId());
        if (pl == null) {
            return Response.status(404).entity("{\"success\":false,\"message\":\"Player not found\"}").build();
        }

        if(pl.getId() != repo.getCurrentTurn()){
            return Response.status(400).entity("{\"success\":false,\"message\":\"It's not your turn\"}").build();
        }

        pl.setPosition(req.getNewPosition());
        Map<String,Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Déplacement réussi");
        resp.put("newPosition", pl.getPosition());
        return Response.ok(resp).build();
    }

    @POST
    @Path("/attack")
    public Response attack(AttackRequest req) {
        Player attacker = repo.getPlayer(req.getPlayerId());
        Player target = repo.getPlayer(req.getTargetId());
        if (attacker == null || target == null) {
            return Response.status(404)
                    .entity("{\"success\":false,\"message\":\"Invalid attacker or target\"}")
                    .build();
        }

        // Vérifie si c'est (ou non) le tour de l'attaquant
        // (Ici, on suppose que si attacker.getId() == currentTurn, c'est INTERDIT.
        //  Adaptez la logique selon vos règles.)
        if (attacker.getId() != repo.getCurrentTurn()) {
            return Response.status(400)
                    .entity("{\"success\":false,\"message\":\"It's not your turn\"}")
                    .build();
        }

        // Vérifie la distance (Manhattan) : doit être exactement de 1
        int[] attackerPos = attacker.getPosition();
        int[] targetPos = target.getPosition();
        int distance = Math.abs(attackerPos[0] - targetPos[0]) + Math.abs(attackerPos[1] - targetPos[1]);
        if (distance != 1) {
            return Response.status(400)
                    .entity("{\"success\":false,\"message\":\"Target is not in range\"}")
                    .build();
        }

        // Applique l'attaque
        int newHp = target.getHp() - 20;
        if (newHp < 0) newHp = 0;
        target.setHp(newHp);

        Map<String,Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Attaque réussie");
        resp.put("targetHp", target.getHp());

        return Response.ok(resp).build();
    }

    @POST
    @Path("/end-turn")
    public Response endTurn(EndTurnRequest req) {
        // Vérifier si le joueur existe
        if (repo.getPlayer(req.getPlayerId()) == null) {
            return Response.status(404)
                    .entity("{\"success\":false,\"message\":\"Player not found\"}")
                    .build();
        }

        // Vérifier si le joueur qui appelle end-turn est bien celui dont c'est le tour (sinon, erreur)
        if (req.getPlayerId() != repo.getCurrentTurn()) {
            return Response.status(400)
                    .entity("{\"success\":false,\"message\":\"It's not your turn\"}")
                    .build();
        }

        // Récupérer la liste de tous les joueurs
        List<Player> all = repo.getAllPlayers();
        // Trouver l'index du joueur courant
        int current = repo.getCurrentTurn();
        int currentIndex = -1;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == current) {
                currentIndex = i;
                break;
            }
        }

        // Déterminer l'index du prochain joueur (round-robin)
        if (!all.isEmpty() && currentIndex >= 0) {
            int nextIndex = (currentIndex + 1) % all.size();
            int nextId = all.get(nextIndex).getId();
            repo.setCurrentTurn(nextId);
            Map<String,Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("nextTurn", nextId);
            return Response.ok(resp).build();
        }

        // Si pas trouvé, renvoyer une erreur
        return Response.status(400)
                .entity("{\"success\":false,\"message\":\"Cannot determine next turn\"}")
                .build();
    }

    @GET
    @Path("/players")
    public Response getPlayers() {
        List<Player> players = repo.getAllPlayers();
        Map<String,Object> resp = new HashMap<>();
        resp.put("players", players);
        return Response.ok(resp).build();
    }

    @POST
    @Path("/chat")
    public Response chat(ChatRequest req) {
        if (repo.getPlayer(req.getPlayerId()) == null) {
            return Response.status(404).entity("{\"success\":false,\"message\":\"Player not found\"}").build();
        }
        Map<String,Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Message envoyé");
        return Response.ok(resp).build();
    }
}
