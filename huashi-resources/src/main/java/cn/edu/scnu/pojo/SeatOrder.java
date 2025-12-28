package cn.edu.scnu.pojo;

public class SeatOrder {
	int seatId;
	int orderId;
	int sessionId;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public SeatOrder(int seatId, int orderId, int sessionId) {
		super();
		this.seatId = seatId;
		this.orderId = orderId;
		this.sessionId = sessionId;
	}
	public SeatOrder() {
		super();
	}
	
}
