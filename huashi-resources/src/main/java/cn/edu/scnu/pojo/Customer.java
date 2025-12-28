package cn.edu.scnu.pojo;

public class Customer {
/*
 *  customer_id int(12) NOT NULL auto_increment PRIMARY KEY,
	password varchar(20) default NULL,
	customer_name varchar(12) default NULL,
	email varchar(40) default NULL,
	phone int(11) default NULL,
	type int(2) default 2
 */
	private Integer customerId;
	private String password;
	private String customerName;
	private String email;
	private String phone;
	private Integer type;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
