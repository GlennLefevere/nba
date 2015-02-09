package nba.com.dao;

import java.sql.Date;
import java.util.List;

import nba.com.entities.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<Game, Long>{
	public List<Game> findByDate(Date date);
}
