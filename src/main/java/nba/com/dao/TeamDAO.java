package nba.com.dao;

import nba.com.entities.Team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDAO extends JpaRepository<Team, Long>{

}
