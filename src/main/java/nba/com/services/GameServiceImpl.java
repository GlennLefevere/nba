package nba.com.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nba.com.dao.TeamDAO;
import nba.com.entities.Game;
import nba.com.entities.Player;
import nba.com.entities.Team;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
	private transient final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	private TeamDAO teamDAO;

	// @Override
	public void getGame() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		try {
			URI uri = new URI(
					"http://data.nba.com/json/cms/noseason/scoreboard/20150205/games.json");
			ClientHttpRequest request = clientHttpRequestFactory.createRequest(
					uri, HttpMethod.GET);
			ClientHttpResponse response = request.execute();
			if (response.getStatusCode() == HttpStatus.OK) {
				InputStream input = response.getBody();
				System.err.println(IOUtils.toString(input, "UTF-8"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Game> findAllGames(int toToday) throws URISyntaxException,
			IOException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, toToday);
		String datum = DATEFORMAT.format(cal.getTime());
		List<Game> games = new ArrayList<Game>();
		String url = "http://data.nba.com/json/cms/noseason/scoreboard/" + datum + "/games.json";
		String myString = getUrlResponse(url);
		String[] temp = myString.split("\"game\":\\[");
		if (temp.length > 1) {
			String[] lol = temp[1].split("\\,");
			Game game = new Game();
			Date date = new Date(cal.getTime().getTime());
			game.setDate(date);
			long teller = 0;
			for (String string : lol) {
				if (string.contains("\"id\"")) {
					if (string.contains("visitor")) {
						Team visitor = makeTeam(lol, teller);
						String controle = lol[(int) teller + 7].split("\"")[3];
						if (!controle.isEmpty()) {
							game.setVisitorScore(Integer.parseInt(controle));
						}
						game.setVisitor(visitor);
					} else if (string.contains("home")) {
						Team home = makeTeam(lol, teller);
						String controle = lol[(int) teller + 7].split("\"")[3];
						if (!controle.isEmpty()) {
							game.setHomeScore(Integer.parseInt(controle));
						}
						game.setHome(home);
					} else {
						if (game != null) {
							if (game.getId() != 0) {
								games.add(game);
								game = new Game();
							}
						}
						String[] split = string.split("\"");
						game.setId(Long.parseLong(split[3]));
					}
				}
				teller++;
			}
			games.add(game);
		}
		return games;
	}

	@Override
	public void getBoxScore(Game game) throws URISyntaxException, IOException {
		//http://data.nba.com/json/cms/noseason/game/20150215/0031400001/boxscore.json
		//http://data.nba.com/json/cms/noseason/game/20121126/0021200013/boxscore.json
		String url = "http://data.nba.com/json/cms/noseason/game/" + DATEFORMAT.format(game.getDate()) + "/00" + game.getId() + "/boxscore.json";
		String myString = getUrlResponse(url);
		String[] temp = myString.split("\"game\":\\{");
		String[] waarden = temp[1].split("\\,");
		int teller = 0;
		for (String string : waarden) {
			if (string.contains("\"id\"")) {
				if (string.contains("visitor")) {
						game.getVisitor().setPlayers(createPlayers(teller, waarden));
				} else if (string.contains("home")) {
					game.getHome().setPlayers(createPlayers(teller, waarden));
				}
			}
			teller++;
		}
	}
	
	private Team makeTeam(String[] list, long teller) {
		long id = Long.parseLong(list[(int) teller].split("\"")[5]);
		Team team = teamDAO.findOne(id);
		if (team != null) {
			return team;
		} else {
			team = new Team();
			team.setId(id);
			team.setCity(list[(int) teller + 2].split("\"")[3]);
			team.setNickname(list[(int) teller + 5].split("\"")[3]);
			teamDAO.save(team);
		}
		return team;
	}
	
	private String getUrlResponse(String url) throws URISyntaxException, IOException {
		//InetSocketAddress adress = new InetSocketAddress("proxy.kamodata.be", 8080);
		//Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, adress);
		URI uri = new URI(url);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		//factory.setProxy(proxy);
		ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);
		ClientHttpResponse response = request.execute();
		if (response.getStatusCode() == HttpStatus.OK) {
			InputStream input = response.getBody();
			return IOUtils.toString(input, "UTF-8");
		}
		return null;
	}
	
	private List<Player> createPlayers(int teller, String[] waarden){
		int tempteller = teller + 42;
		List<Player> players = new ArrayList<Player>();
		do {
			Player player = new Player();
			if (tempteller == teller + 42) {
				player.setFirstName(waarden[tempteller].split("\"")[7]);
			} else {
				player.setFirstName(waarden[tempteller].split("\"")[3]);
			}
			player.setLastName(waarden[tempteller + 1].split("\"")[3]);
			player.setNumber(waarden[tempteller + 2].split("\"")[3]);
			player.setPosition(waarden[tempteller + 4].split("\"")[3]);
			String timeplayed = waarden[tempteller + 5].split("\"")[3] + ":" + waarden[tempteller + 6].split("\"")[3];
			player.setTimePlayed(timeplayed);
			player.setPoints(Integer.parseInt(waarden[tempteller + 7].split("\"")[3]));
			//System.out.println(waarden[tempteller + 8].split("\"")[3]);
			//System.out.println(waarden[tempteller + 9].split("\"")[3]);
			player.setFieldGoalsMade(Integer.parseInt(waarden[tempteller + 8].split("\"")[3]));
			player.setFieldGoalAttempts(Integer.parseInt(waarden[tempteller + 9].split("\"")[3]));
			//player.setLastName(waarden[tempteller + 10].split("\"")[3]);
			player.setFreethrowsMade(Integer.parseInt(waarden[tempteller + 11].split("\"")[3]));
			player.setFreethrowsAttempted(Integer.parseInt(waarden[tempteller + 12].split("\"")[3]));
			player.setThreepointersMade(Integer.parseInt(waarden[tempteller + 13].split("\"")[3]));
			player.setThreepointersAttempt(Integer.parseInt(waarden[tempteller + 14].split("\"")[3]));
			player.setOffensiveRebounds(Integer.parseInt(waarden[tempteller + 15].split("\"")[3]));
			player.setDeffensiveRebounds(Integer.parseInt(waarden[tempteller + 16].split("\"")[3]));
			player.setAssists(Integer.parseInt(waarden[tempteller + 17].split("\"")[3]));
			player.setFouls(Integer.parseInt(waarden[tempteller + 18].split("\"")[3]));
			player.setSteals(Integer.parseInt(waarden[tempteller + 19].split("\"")[3]));
			player.setTurnovers(Integer.parseInt(waarden[tempteller + 20].split("\"")[3]));
			//player.setBlocks(blocks);(waarden[tempteller + 21].split("\"")[3]);
			player.setBlocks(Integer.parseInt(waarden[tempteller + 22].split("\"")[3]));
			//player.setLastName(waarden[tempteller + 23].split("\"")[3]);
			//player.setLastName(waarden[tempteller + 24].split("\"")[3]);
			//player.setLastName(waarden[tempteller + 25].split("\"")[3]);
			players.add(player);
			if(tempteller + 25 > waarden.length -1){
				break;
			}
			else{
				tempteller += 25;
			}
		} while (waarden[tempteller].contains("first_name"));
		/*for (Player player : players) {
			System.out.println(player.getFirstName());
			for (Field field : player.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(player);
					if (value != null) {
						System.out.println(field.getName() + "=" + value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}*/
		return players;
	}

}
