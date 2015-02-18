package nba.com.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import nba.com.entities.Game;
import nba.com.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class IndexBean implements Serializable {
	private static final long serialVersionUID = 2227888617953475573L;
	private List<Game> games;
	private transient GameService gameService;
	private int date;

	@Autowired
	public IndexBean(GameService gameService) {
		this.gameService = gameService;
	}

	@PostConstruct
	public void init() {
		date = 0;
		try {
			games = gameService.findAllGames(date);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void updateGamesDown(){
		date--;
		try {
			games = gameService.findAllGames(date);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGamesUp(){
		date ++;
		try {
			games = gameService.findAllGames(date);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, date);
		return formatter.format(cal.getTime());
	}

	public void getPlayers(Game game) throws URISyntaxException, IOException{
		gameService.getBoxScore(game);
	}
}
