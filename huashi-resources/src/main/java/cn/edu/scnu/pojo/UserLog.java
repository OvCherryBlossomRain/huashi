package cn.edu.scnu.pojo;

import java.sql.Timestamp;

public class UserLog {
	private Integer logId;
	private Timestamp time;
	private Integer customerId;
	private String LogInfo;
	
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getLogInfo() {
		return LogInfo;
	}
	public void setLogInfo(String logInfo) {
		LogInfo = logInfo;
	}
	
}
