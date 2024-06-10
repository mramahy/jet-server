package com.jet.game.service;

import static com.jet.game.domain.GameState.OPEN;

import com.jet.game.domain.Game;
import com.jet.game.domain.Move;
import com.jet.game.dto.GameDto;
import com.jet.game.error.GameNotFoundException;
import com.jet.game.repository.GameRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository repository;

  public GameService(GameRepository repository) {
    this.repository = repository;
  }

  public GameDto createNewGame(GameDto gameRequest) {
        Game newGame = Game.builder()
            .id(UUID.randomUUID())
            .state(OPEN)
            .mode(gameRequest.getMode())
            .number(gameRequest.getNumber()).build();
        repository.insertGame(newGame);
        return newGame.toDto();
    }

    public GameDto checkGameState(UUID gameId) throws GameNotFoundException {
        return repository.findGameById(gameId).orElseThrow(GameNotFoundException::new).toDto();
    }

    public GameDto performMove(UUID gameId, Move move) throws GameNotFoundException {
        Game game = repository.findGameById(gameId).orElseThrow(GameNotFoundException::new);
        return game.applyMoveToGame(move).toDto();
    }

    public GameDto joinAvailableGame() throws GameNotFoundException {
        Game gameToJoin = repository.findOpenGame().orElseThrow(GameNotFoundException::new);
        gameToJoin.startGame();
        return gameToJoin.toDto();
    }
}