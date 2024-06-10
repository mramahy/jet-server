package com.jet.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jet.game.domain.GameMode;
import com.jet.game.domain.GameState;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
public class GameDto implements Serializable {

    private GameState state;
    private String id;
    private Integer number;
    private GameMode mode;
}