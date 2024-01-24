package mock;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.ArrayList;
import java.util.List;

public class GameRepositoryMock implements GameRepository {
    private final List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }



    @Override
    public Game findById(int id) {
        return games.get(id);
    }

    @Override
    public String findByName(String name) {
        for(Game game: games){
            if (name.equals(game.getName())){
                return name;
            }
        }
        return null;
    }

    public List<Game> showAll() {
        return new ArrayList<>(games);
    }
    public int getNumberOfGames() {
        return games.size();
    }
}
