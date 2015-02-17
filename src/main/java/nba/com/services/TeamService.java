package nba.com.services;

import java.util.List;

import nba.com.entities.Team;

public interface TeamService {
	public List<Team> findAll();
	public void update(Team team);

}
