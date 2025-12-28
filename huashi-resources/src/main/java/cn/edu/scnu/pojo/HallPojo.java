package cn.edu.scnu.pojo;


public class HallPojo {
	String description;
	double rate;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "HallPojo [description=" + description + ", rate=" + rate + "]";
	}
	
	
}
