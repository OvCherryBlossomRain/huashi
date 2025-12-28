package cn.edu.scnu.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.customer.service.CustomerService;
import cn.edu.scnu.pojo.Customer;
import cn.edu.scnu.pojo.UserLog;
import cn.edu.scnu.utils.CookieUtils;
import cn.edu.scnu.vo.SysResult;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerservice;

	@RequestMapping("/customer/manage/checkEmail")
	public SysResult checkEmail(String email) {
		Integer a = customerservice.checkEmail(email);
		if (a == 0) {
			System.out.println("ok");
			return SysResult.ok();
		} else {
			System.out.println("gg");
			return SysResult.build(201, "已存在", null);
		}
	}

	@RequestMapping("/customer/manage/save")
	public SysResult saveCustomer(Customer customer) {
		Integer a = customerservice.checkEmail(customer.getEmail());
		// System.out.println("1");
		if (a > 0) {
			System.out.println("2");
			return SysResult.build(201, "已存在", null);
		}

		try {
			System.out.println(customer.getCustomerName());
			customerservice.saveCustomer(customer);
			return SysResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}

	@RequestMapping("/customer/manage/login")
	public SysResult doLogin(Customer customer, HttpServletRequest request, HttpServletResponse response) {
		String ticket = customerservice.doLogin(customer);
		if (StringUtils.isNotEmpty(ticket)) {

			CookieUtils.setCookie(request, response, "EM_TICKET", ticket, 60 * 30);
			return SysResult.ok();
		} else {
			return SysResult.build(201, "登陆失败", null);
		}
	}

	@RequestMapping("/customer/manage/query/{ticket}")
	public SysResult checkLogin(@PathVariable String ticket) {
		String userJson = customerservice.queryCustomerJson(ticket);
		System.out.println(userJson);
		if (StringUtils.isNotEmpty(userJson)) {
			return SysResult.build(200, "ok", userJson);
		} else {
			return SysResult.build(201, "", null);
		}
	}

	@RequestMapping("/customer/manage/loginout/{ticket}")
	public SysResult loginout(@PathVariable String ticket) {
		Integer a = customerservice.loginout(ticket);
		if (a == 1) {
			return SysResult.build(200, "ok", null);
		} else {
			return SysResult.build(201, "", null);
		}
	}

	@RequestMapping("/customer/manage/queryLog/{customerId}")
	public SysResult checkLogin(@PathVariable Integer customerId) {
		List<UserLog> CustomerLog = customerservice.queryCustomerLogJson(customerId);
		// System.out.println(CustomerLog.size());
		if (!CustomerLog.isEmpty()) {
			return SysResult.build(200, "ok", CustomerLog);
		} else {
			return SysResult.build(201, "", null);
		}
	}

	@RequestMapping("/customer/manage/updateCustomer")
	public SysResult updateCustomerInfo(Customer customer, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(customer.getEmail()+customer.getCustomerName()+customer.getPhone()+customer.getCustomerId());
		String a = customerservice.updateCustomerInfo(customer);
		if (a != "" && StringUtils.isNotEmpty(a)) {
			System.out.println("ok");
			CookieUtils.deleteCookie(request, response, "EM_TICKET");
			CookieUtils.setCookie(request, response, "EM_TICKET", a);
			return SysResult.ok();
		} else {
			System.out.println("gg");
			return SysResult.build(201, "gg", null);
		}
	}

	@RequestMapping("/customer/manage/updatePassword")
	public SysResult updatePassword(Customer customer){
		Integer a = customerservice.updatePassword(customer);
		if (a == 0) {
			System.out.println("ok");
			return SysResult.ok();
		} else {
			System.out.println("gg");
			return SysResult.build(201, "已存在", null);
		}
	}

	@RequestMapping("/customer/manage/addLog")
	public Integer addLog(Integer customerId,String LogInfo){
		System.out.println(customerId+LogInfo);
		UserLog ur=new UserLog();
		ur.setCustomerId(customerId);
		ur.setLogInfo(LogInfo);
		try {
			System.out.println(ur.getCustomerId());
			customerservice.addLog(ur);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
