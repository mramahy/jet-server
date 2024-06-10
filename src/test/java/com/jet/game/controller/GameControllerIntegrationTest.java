package com.jet.game.controller;

import com.jet.game.domain.GameState;
import com.jet.game.dto.GameDto;
import com.jet.game.service.GameService;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.jet.game.domain.GameState.OPEN;
import static com.jet.game.domain.Move.PLUS;
import static com.jet.game.fixtures.GameDtoFixture.gameDto;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerIntegrationTest {
  // assuming that application is running on port 8080

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private GameService gameService;

  @Test
  void testStartGame() {
    //...
    ResponseEntity<GameDto> response = restTemplate.postForEntity("/games", gameDto(), GameDto.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(Objects.requireNonNull(response.getBody()).getId());
    assertEquals(30, response.getBody().getNumber());
    assertEquals(OPEN, response.getBody().getState());
  }

  @Test
  void testJoinGame() {
    //...

    var game = gameService.createNewGame(GameDto.builder().number(88).state(OPEN).build());
    ResponseEntity<GameDto> responseJoinGame = restTemplate.exchange("/games", HttpMethod.PUT, null, GameDto.class);
    assertEquals(HttpStatus.OK, responseJoinGame.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseJoinGame.getBody()).getId());
    assertEquals(GameState.ONGOING, responseJoinGame.getBody().getState());
  }

  @Test
  void testPerformMove() {
    //...
    ResponseEntity<GameDto> response = restTemplate.postForEntity("/games", Optional.of(99), GameDto.class);
    String gameId = response.getBody().getId();
    restTemplate.put("/games/" + gameId + "/moves", PLUS, Void.class);
    ResponseEntity<GameDto> afterMoveResponse = restTemplate.getForEntity("/games/" + gameId, GameDto.class);
    assertEquals(HttpStatus.OK, afterMoveResponse.getStatusCode());
    assertEquals(100, afterMoveResponse.getBody().getNumber());
  }

  @Test
  void testGetGameState() {
    //...
    ResponseEntity<GameDto> response = restTemplate.postForEntity("/games", Optional.of(99), GameDto.class);
    String gameId = response.getBody().getId();
    restTemplate.put("/games/" + gameId + "/moves", PLUS, Void.class);
    ResponseEntity<GameDto> afterMoveResponse = restTemplate.getForEntity("/games/" + gameId, GameDto.class);
    assertEquals(HttpStatus.OK, afterMoveResponse.getStatusCode());
    assertEquals(100, afterMoveResponse.getBody().getNumber());
  }
}