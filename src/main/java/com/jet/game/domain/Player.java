package com.jet.game.domain;

public class Player {

  private Long id;
  private boolean winner = false;

  private Player(Builder builder) {
    this.id = builder.id;
    this.winner = builder.winner;
  }

  public static class Builder {
    private Long id;
    private boolean winner;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder winner(boolean winner) {
      this.winner = winner;
      return this;
    }

    public Player build() {
      return new Player(this);
    }
  }

  public Long getId() {
    return id;
  }

  public boolean isWinner() {
    return winner;
  }



}
