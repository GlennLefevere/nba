/**
 * (c) 2015 ADMB. All rights reserved.
 */
package nba.com.entities;

/**
 * @author infglef
 *
 */
public class Player {
	private String firstName;
	private String lastName;
	private String number;
	private String position;
	private String timePlayed;
	private int points;
	private int fieldGoalsMade;
	private int fieldGoalAttempts;
	private int freethrowsMade;
	private int freethrowsAttempted;
	private int threepointersMade;
	private int threepointersAttempt;
	private int offensiveRebounds;
	private int deffensiveRebounds;
	private int assists;
	private int fouls;
	private int turnovers;
	private int steals;
	private int blocks;
	private boolean strater;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 * the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 * the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 * the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 * the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the timePlayed
	 */
	public String getTimePlayed() {
		return timePlayed;
	}

	/**
	 * @param timePlayed
	 * the timePlayed to set
	 */
	public void setTimePlayed(String timePlayed) {
		this.timePlayed = timePlayed;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points
	 * the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the fieldGoalsMade
	 */
	public int getFieldGoalsMade() {
		return fieldGoalsMade;
	}

	/**
	 * @param fieldGoalsMade
	 * the fieldGoalsMade to set
	 */
	public void setFieldGoalsMade(int fieldGoalsMade) {
		this.fieldGoalsMade = fieldGoalsMade;
	}

	/**
	 * @return the filedGoelAttempts
	 */
	public int getFieldGoalAttempts() {
		return fieldGoalAttempts;
	}

	/**
	 * @param fieldGoelAttempts
	 * the filedGoelAttempts to set
	 */
	public void setFieldGoalAttempts(int fieldGoalAttempts) {
		this.fieldGoalAttempts = fieldGoalAttempts;
	}

	/**
	 * @return the freethrowsMade
	 */
	public int getFreethrowsMade() {
		return freethrowsMade;
	}

	/**
	 * @param freethrowsMade
	 * the freethrowsMade to set
	 */
	public void setFreethrowsMade(int freethrowsMade) {
		this.freethrowsMade = freethrowsMade;
	}

	/**
	 * @return the freethrowsAttempted
	 */
	public int getFreethrowsAttempted() {
		return freethrowsAttempted;
	}

	/**
	 * @param freethrowsAttempted
	 * the freethrowsAttempted to set
	 */
	public void setFreethrowsAttempted(int freethrowsAttempted) {
		this.freethrowsAttempted = freethrowsAttempted;
	}

	/**
	 * @return the offensiveRebounds
	 */
	public int getOffensiveRebounds() {
		return offensiveRebounds;
	}

	/**
	 * @param offensiveRebounds
	 * the offensiveRebounds to set
	 */
	public void setOffensiveRebounds(int offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	/**
	 * @return the deffensiveRebounds
	 */
	public int getDeffensiveRebounds() {
		return deffensiveRebounds;
	}

	/**
	 * @param deffensiveRebounds
	 * the deffensiveRebounds to set
	 */
	public void setDeffensiveRebounds(int deffensiveRebounds) {
		this.deffensiveRebounds = deffensiveRebounds;
	}

	/**
	 * @return the assists
	 */
	public int getAssists() {
		return assists;
	}

	/**
	 * @param assists
	 * the assists to set
	 */
	public void setAssists(int assists) {
		this.assists = assists;
	}

	/**
	 * @return the fouls
	 */
	public int getFouls() {
		return fouls;
	}

	/**
	 * @param fouls
	 * the fouls to set
	 */
	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	/**
	 * @return the turnovers
	 */
	public int getTurnovers() {
		return turnovers;
	}

	/**
	 * @param turnovers
	 * the turnovers to set
	 */
	public void setTurnovers(int turnovers) {
		this.turnovers = turnovers;
	}

	/**
	 * @return the steals
	 */
	public int getSteals() {
		return steals;
	}

	/**
	 * @param steals
	 * the steals to set
	 */
	public void setSteals(int steals) {
		this.steals = steals;
	}

	/**
	 * @return the blocks
	 */
	public int getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks
	 * the blocks to set
	 */
	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the strater
	 */
	public boolean isStrater() {
		return strater;
	}

	/**
	 * @param strater
	 * the strater to set
	 */
	public void setStrater(boolean strater) {
		this.strater = strater;
	}

	/**
	 * @return the threepointersMade
	 */
	public int getThreepointersMade() {
		return threepointersMade;
	}

	/**
	 * @param threepointersMade
	 * the threepointersMade to set
	 */
	public void setThreepointersMade(int threepointersMade) {
		this.threepointersMade = threepointersMade;
	}

	/**
	 * @return the threepointersAttempt
	 */
	public int getThreepointersAttempt() {
		return threepointersAttempt;
	}

	/**
	 * @param threepointersAttempt
	 * the threepointersAttempt to set
	 */
	public void setThreepointersAttempt(int threepointersAttempt) {
		this.threepointersAttempt = threepointersAttempt;
	}

}