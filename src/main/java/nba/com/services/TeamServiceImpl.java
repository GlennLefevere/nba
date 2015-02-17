package nba.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.com.dao.TeamDAO;
import nba.com.entities.Team;

@Service
public class TeamServiceImpl implements TeamService{
	private TeamDAO teamDao;
	
	@Autowired
	public TeamServiceImpl(TeamDAO teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public List<Team> findAll() {
		return teamDao.findAll();
	}

	@Override
	public void update(Team team) {
		teamDao.save(team);
	}

}
