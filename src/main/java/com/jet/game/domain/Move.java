package com.jet.game.domain;

public enum Move {
  PLUS(1), MINUS(-1), ZERO(0);

  private final Integer moveValue;

  Move(Integer moveValue) {
    this.moveValue = moveValue;
  }

  public Integer getMoveValue(){
    return moveValue;
  }
}
