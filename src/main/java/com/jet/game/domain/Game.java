package com.jet.game.domain;

import static com.jet.game.domain.GameState.FINISHED;
import static com.jet.game.domain.GameState.ONGOING;

import com.jet.game.dto.GameDto;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Game {
    private UUID id;
    private Integer number;
    private GameState state;
    private GameMode mode;


    public Game applyMoveToGame(Move move) {
        number += move.getMoveValue();
        number /= 3;
        if(number.equals(1)){
            state = FINISHED;
        }
        return this;
    }

    public void startGame() {
        state = ONGOING;
    }

    public GameDto toDto() {
        return GameDto.builder()
            .state(state)
            .mode(mode)
            .id(id.toString())
            .number(number)
            .mode(mode).build();
    }
}