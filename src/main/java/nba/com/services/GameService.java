package nba.com.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import nba.com.entities.Game;

public interface GameService {
	public List<Game> findAllGames(int toToday)throws URISyntaxException,
	IOException;
}
