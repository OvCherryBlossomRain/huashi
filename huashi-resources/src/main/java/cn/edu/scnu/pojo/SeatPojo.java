package cn.edu.scnu.pojo;

public class SeatPojo {
	private int seatId;
	private int seatRow;
	private int seatColumn;
	private int hallId;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public int getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	@Override
	public String toString() {
		return "SeatPojo [seatId=" + seatId + ", seatRow=" + seatRow + ", seatColumn=" + seatColumn + ", hallId="
				+ hallId + "]";
	}
	
	
}
