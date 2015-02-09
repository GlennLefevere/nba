package nba.com.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="games")
public class Game {
	@Id
	private long id;
	@ManyToOne
	private Team visitor;
	@ManyToOne
	private Team home;
	private int homeScore;
	private int visitorScore;
	private Date date;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Team getVisitor() {
		return visitor;
	}

	public void setVisitor(Team visitor) {
		this.visitor = visitor;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int home_score) {
		this.homeScore = home_score;
	}

	public int getVisitorScore() {
		return visitorScore;
	}

	public void setVisitorScore(int visitor_score) {
		this.visitorScore = visitor_score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
