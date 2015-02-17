package nba.com.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import nba.com.entities.Team;
import nba.com.services.TeamService;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class TeamBean {
	private List<Team> teams;
	private Team team;
	private UploadedFile file;
	private transient TeamService teamService;

	@Autowired
	public TeamBean(TeamService teamService) {
		this.teamService = teamService;
	}

	@PostConstruct
	public void init() {
		teams = teamService.findAll();
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void uploadFile() {
		byte[] file = new byte[this.file.getContents().length];
		System.arraycopy(this.file.getContents(), 0, file, 0,
				this.file.getContents().length);
		if (team != null) {
			team.setImage(file);
			teamService.update(team);
		}
		System.out.println(this.file.getContentType());
	}

}