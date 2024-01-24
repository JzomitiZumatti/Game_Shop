package service;

import mock.GameRepositoryMock;
import org.example.model.Game;
import org.example.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {

    private GameService gameService;
    private GameRepositoryMock gameRepositoryMock;

    @BeforeEach
    void setUp() {
        gameRepositoryMock = new GameRepositoryMock(List.of(
                Game.builder().id(1).name("Game1").build(),
                Game.builder().id(1).name("Game2").build(),
                Game.builder().id(1).name("Game3").build()
        ));
        gameService = new GameService(gameRepositoryMock);
    }

    @Test
    void testFindById() {
        int gameId = 1;
        Game foundGame = gameService.findById(gameId);
        assertEquals(gameId, foundGame.getId());
    }

    @Test
    void testFindByName() {
        String gameName = "Game2";
        String foundName = gameService.findByName(gameName);
        assertEquals(gameName, foundName);
    }

    @Test
    void testShowAll() {
        List<Game> allGames = gameService.showAll();
        int expectedSize = allGames.size();
        assertEquals(expectedSize, gameRepositoryMock.getNumberOfGames());
    }

    @Test
    void testGetNumberOfGames() {
        int numberOfGames = gameService.getNumberOfGames();
        assertEquals(gameRepositoryMock.getNumberOfGames(), numberOfGames);
    }
}

