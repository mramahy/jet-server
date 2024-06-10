package com.jet.game.fixtures;

import static com.jet.game.domain.GameMode.AUTOMATIC;

import com.jet.game.domain.Game;
import com.jet.game.domain.GameState;
import java.util.UUID;

public class GameFixture {

  public static Game game(){
    return  Game.builder()
        .mode(AUTOMATIC)
        .id(UUID.randomUUID())
        .state(GameState.OPEN)
        .number(30).build();
  }
}
