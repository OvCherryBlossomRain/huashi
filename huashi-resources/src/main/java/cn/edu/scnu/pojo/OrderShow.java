package cn.edu.scnu.pojo;

import java.sql.Timestamp;
import java.util.List;

public class OrderShow {
	private Timestamp buyTime;
	private String description;
	private Integer money;
	private String movieName;
	private Timestamp startTime;
	private Timestamp endTime;
	private List<SeatPojo> seatInfo;
	private Integer orderId;

	public OrderShow(Timestamp buyTime, String description, Integer money, String movieName, Timestamp startTime,
			Timestamp endTime, List<SeatPojo> seatInfo, Integer orderId) {
		super();
		this.buyTime = buyTime;
		this.description = description;
		this.money = money;
		this.movieName = movieName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.seatInfo = seatInfo;
		this.setOrderId(orderId);
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

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

	public List<SeatPojo> getSeatInfo() {
		return seatInfo;
	}

	public void setSeatInfo(List<SeatPojo> seatInfo) {
		this.seatInfo = seatInfo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
