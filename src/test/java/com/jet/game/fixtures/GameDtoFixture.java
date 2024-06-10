package com.jet.game.fixtures;

import static com.jet.game.fixtures.GameFixture.game;

import com.jet.game.dto.GameDto;

public class GameDtoFixture {

  public static GameDto gameDto(){
    return game().toDto();
  }

}
