package cn.edu.scnu.customer.mapper;

import java.util.List;

import cn.edu.scnu.pojo.Customer;
import cn.edu.scnu.pojo.UserLog;

public interface CustomerMapper {

	Integer queryEmail(String email);

	void saveCustomer(Customer customer);

	Customer queryUserByEmailAndPassword(Customer customer);

	void saveLog(UserLog ur);

	List<UserLog> queryCustomerLogJson(Integer customerId);

	void updateCustomerInfo(Customer customer);

	void updatePassword(Customer customer);
	
}
