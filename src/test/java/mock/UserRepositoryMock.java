package mock;

import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.util.List;

public class UserRepositoryMock implements UserRepository {
    private List<User> users;

    public UserRepositoryMock(List<User> users) {
        this.users = users;
    }

    @Override
    public User register(User user) {
        users = List.of(user);
        return users.get(0);
    }

    @Override
    public User findById(int userId) {
        for(User user: users){
            if (userId == (user.getId())){
                return user;
            }
        }
        return User.builder().build();
    }

    @Override
    public User findByNickname(String nickname) {
        for(User user: users){
            if (nickname.equals(user.getNickname())){
                return user;
            }
        }
        return User.builder().build();
    }

    @Override
    public void recharge(int amount, int userId) {

    }


    @Override
    public List<Integer> userGames(int id) {
        return List.of(1, 2, 3, 4, 5);
    }

    @Override
    public void addGame(int userId, int gameId) {

    }
}
