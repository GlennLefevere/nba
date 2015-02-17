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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, toToday);
		String datum = formatter.format(cal.getTime());
		List<Game> games = new ArrayList<Game>();
		URI url = new URI("http://data.nba.com/json/cms/noseason/scoreboard/"
				+ datum + "/games.json");
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		ClientHttpRequest request = factory.createRequest(url, HttpMethod.GET);
		ClientHttpResponse response = request.execute();
		if (response.getStatusCode() == HttpStatus.OK) {
			InputStream input = response.getBody();
			String myString = IOUtils.toString(input, "UTF-8");
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
		}
		return games;
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

}
