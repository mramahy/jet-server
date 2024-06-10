package com.jet.game.controller;

import com.jet.game.domain.Move;
import com.jet.game.dto.GameDto;
import com.jet.game.error.GameNotFoundException;
import com.jet.game.service.GameService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GameDto> startGame(@RequestBody GameDto gameRequest) {
        GameDto gameDto = service.createNewGame(gameRequest);
        return ResponseEntity.ok(gameDto);
    }

    @PutMapping
    public ResponseEntity<GameDto> joinGame() throws GameNotFoundException {
        GameDto gameDto = service.joinAvailableGame();
        return ResponseEntity.ok(gameDto);
    }

    @PostMapping("/{gameId}/moves/{move}")
    public ResponseEntity<GameDto> makeMove(@PathVariable("gameId") String gameId, @PathVariable("move") String move)
        throws GameNotFoundException {
        UUID gameUUID = UUID.fromString(gameId);
        GameDto game = service.performMove(gameUUID, Move.valueOf(move.toUpperCase()));
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGameState(@PathVariable("gameId") String gameId)
        throws GameNotFoundException {
        GameDto game = service.checkGameState(UUID.fromString(gameId));
        return ResponseEntity.ok(game);
    }
}