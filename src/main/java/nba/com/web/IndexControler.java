package nba.com.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nba.com.entities.Game;
import nba.com.services.GameService;
import nba.com.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexControler {
	private static final String VIEW = "index";
	@Autowired
	private GameService gameService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView showGames() {
		ModelAndView view = new ModelAndView(VIEW);
		
		view.addObject("games", getGames(0)).addObject("day", 0);
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "day")
	ModelAndView showGamesByDay(@RequestParam("day") int day,HttpServletRequest request) {
		ModelAndView view = new ModelAndView(VIEW);
		
		view.addObject("games", getGames(0)).addObject("day", 0);
		return view;
	}
	
	private List<Game> getGames(int teller){
		List<Game> games = new ArrayList<Game>();
		try {
			games = gameService.findAllGames(teller);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return games;
	}

}
