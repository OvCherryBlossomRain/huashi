package cn.edu.scnu.pojo;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class SessionPojo {
	Timestamp startTime;
	Timestamp endTime;
	String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	int movieId;
	int sessionId;
	int hallId;
	int ticketmoney;
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	
	public int getTicketmoney() {
		return ticketmoney;
	}
	public void setTicketmoney(int ticketmoney) {
		this.ticketmoney = ticketmoney;
	}
	@Override
	public String toString() {
		return "SessionPojo [startTime=" + startTime + ", endTime=" + endTime + ", movieId=" + movieId + ", sessionId="
				+ sessionId + "]";
	}
	
}
