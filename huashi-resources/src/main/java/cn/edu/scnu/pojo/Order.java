package cn.edu.scnu.pojo;

import java.sql.Timestamp;

public class Order {
	int orderId;
	Timestamp buyTime;
	int money;
	int hallId;
	int customerId;
	int movieId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Timestamp getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHall_id(int hallId) {
		this.hallId = hallId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Order(int orderId, Timestamp buyTime, int money, int hall_id, int customerId, int movieId) {
		super();
		this.orderId = orderId;
		this.buyTime = buyTime;
		this.money = money;
		this.hallId = hall_id;
		this.customerId = customerId;
		this.movieId = movieId;
	}
	public Order() {
		super();
	}
	
	
	
}
