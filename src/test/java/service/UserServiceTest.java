package service;

import mock.UserRepositoryMock;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    private UserService userService;
    private UserRepositoryMock userRepositoryMock;

    @BeforeEach
    void setUp() {
        userRepositoryMock = new UserRepositoryMock(List.of(
                User.builder().id(1).nickname("Dealon").build(),
                User.builder().id(2).nickname("Breadly").build()
        ));
        userService = new UserService(userRepositoryMock);
    }

    @Test
    void testFindById() {
        int userId = 1;
        User actualUser = userService.findById(userId);
        assertEquals(userId, actualUser.getId());
    }

    @Test
    void testFindByNickname() {
        String nickname = "Dealon";
        User actualUser = userService.findByNickname(nickname);
        assertEquals(nickname, actualUser.getNickname());
    }

    @Test
    void testRegister() {
        User userToRegister = User.builder().id(3).nickname("Logan").build();
        User registeredUser = userService.register(userToRegister);
        assertEquals(userToRegister, registeredUser);
    }
    @Test
    void testUserGames() {
        int userId = 1;
        List<Integer> games = userService.userGames(userId);
        assertEquals(List.of(1, 2, 3, 4, 5), games);
    }

}
