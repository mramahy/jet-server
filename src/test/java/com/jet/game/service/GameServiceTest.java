package com.jet.game.service;

import static com.jet.game.domain.GameMode.AUTOMATIC;
import static com.jet.game.domain.GameMode.MANUAL;
import static com.jet.game.fixtures.GameFixture.game;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.jet.game.domain.GameState;
import com.jet.game.domain.Move;
import com.jet.game.dto.GameDto;
import com.jet.game.error.GameNotFoundException;
import com.jet.game.repository.GameRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository repository;

    @Test
    void testPerformMove() throws GameNotFoundException {
        GameDto gameDto= GameDto.builder().number(3).mode(MANUAL).build();
        String id = gameService.createNewGame(gameDto).getId();
        GameDto updatedGameDto = gameService.performMove(UUID.fromString(id), Move.MINUS);
        Integer updatedNumber = updatedGameDto.getNumber();
        assertEquals(1, updatedNumber);
    }

    @Test
    void testJoinAvailableGame() throws GameNotFoundException {
        when(repository.findOpenGame()).thenReturn(Optional.of(game()));
        GameDto gameDto = gameService.joinAvailableGame();
        assertNotNull(gameDto);
        assertEquals(GameState.ONGOING, gameDto.getState());
    }

    @Test
    void testJoinGameWhenNoOpenGames() {
        Assertions.assertThrows(GameNotFoundException.class, () -> gameService.joinAvailableGame());
	}


}