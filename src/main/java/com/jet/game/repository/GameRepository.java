package com.jet.game.repository;

import static com.jet.game.domain.GameState.OPEN;

import com.jet.game.domain.Game;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {
  private final HashMap<UUID, Game> games = new HashMap<>();

  public Game insertGame(Game newGame) {
    return games.put(newGame.getId(),newGame);
  }

  public Optional<Game> findOpenGame() {
    return games.values().stream().filter(game -> game.getState() == OPEN).findFirst();
  }

  public Optional<Game> findGameById(UUID gameId) {
    return Optional.of(games.get(gameId));
  }
}
