package cn.edu.scnu.controller;


import java.sql.Timestamp;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.OrderShow;
import cn.edu.scnu.pojo.SeatOrder;
import cn.edu.scnu.pojo.SeatPojo;
import cn.edu.scnu.pojo.SessionPojo;
import cn.edu.scnu.pojo.UserLog;
import cn.edu.scnu.service.OrderService;
import cn.edu.scnu.vo.SysResult;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/order/manage/getSeats/{sessionId}")
	public SysResult getSeats(@PathVariable Integer sessionId) throws JsonProcessingException{
		List<SeatPojo> list = orderService.getSeats(sessionId);
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writeValueAsString(list);
//		for (SeatPojo seat : list) {
//			System.out.println(seat);
//		}
		return SysResult.build(200, "success", dataJson);
	}
	
	@RequestMapping("/order/manage/bookMovie/{movieId}")
    public SysResult bookMovie(@PathVariable Integer movieId) throws JsonProcessingException {
        List<SessionPojo> list = orderService.getSessionsByMid(movieId);
        ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writeValueAsString(list);
        for (SessionPojo sessionPojo : list) {
        	System.out.println(sessionPojo);
        }
        System.out.println(dataJson);
        return SysResult.build(200, "success", dataJson);
    }
	@RequestMapping("/order/manage/getMoney/{sessionId}")
    public SysResult getSessionTicketMoney(@PathVariable Integer sessionId) throws JsonProcessingException {
        List<SessionPojo> list = orderService.getSessionTicketMoney(sessionId);
        ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writeValueAsString(list);
//        for (SessionPojo sessionPojo : list) {
//        	System.out.println(sessionPojo);
//        }
        System.out.println(dataJson);
        return SysResult.build(200, "success", dataJson);
    }
	
	@PostMapping("/order/manage/insertOrder")
	public SysResult insertOrder(
			@RequestParam("keyword") String keyword,
			@RequestParam("money") String money,
			@RequestParam("customerId") Integer customerId) 
	throws JsonProcessingException {
//		try {
			if (customerId == null) {
				customerId = 1;
			}
			System.out.println(keyword);
			System.out.println(money);
			System.out.println(customerId);
			String[] ids = keyword.split("-");
			int sessionId = Integer.parseInt(ids[0]);
			Order maxOrder = orderService.getMaxOrder();
			if (maxOrder == null) {
				maxOrder = new Order();
				maxOrder.setOrderId(1);
			}
			SessionPojo session = orderService.getSessionsBySid(sessionId);
			Order newOrder = new Order();
//			System.out.println(maxOrder);
			System.out.println(session);
			newOrder.setOrderId(maxOrder.getOrderId() + 1);
			newOrder.setBuyTime(new Timestamp(System.currentTimeMillis()));
			newOrder.setHall_id(session.getHallId());
			newOrder.setCustomerId(customerId);
			newOrder.setMoney(Integer.parseInt(money));
			newOrder.setMovieId(session.getMovieId());
//			System.out.println("hha");
//			System.out.println(newOrder.getMoney());
			orderService.addNewOrder(newOrder);
//			int orderId = 0;
		    for (int i = 1; i < ids.length; i++) {
//		    	System.out.println(ids[i]);
		    	int seatId = Integer.parseInt(ids[i]);
		    	SeatOrder seatOrder = new SeatOrder(seatId, newOrder.getOrderId(), sessionId);
//		    	System.out.println(seatOrder);
		    	orderService.insertSeatOrder(seatOrder);
		    }
		    
//		    userLog功能实现
		    UserLog ur=new UserLog();
			ur.setCustomerId(newOrder.getCustomerId());
			ur.setLogInfo("您的订单支付成功，不要错过电影哦！");
//			Integer ans=restTemplate.postForObject("http://localhost:9005/zuul-customer/customer/manage/addLog", ur, Integer.class);
			String url="http://localhost:9005/zuul-customer/customer/manage/addLog?customerId="+ur.getCustomerId()+"&LogInfo="+ur.getLogInfo();
			System.out.println(url);
			Integer ans=restTemplate.getForObject(url, Integer.class);
		    if(ans==1){
		    	System.out.println("插入成功！");
		    }
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return SysResult.build(400, "error", null);
//		}
		
		return SysResult.build(200, "success", null);
    }
	
	//根据customer_id找到订单信息
	@RequestMapping("/order/manage/getAllOrders/{customerId}")
	public SysResult getAllOrders(@PathVariable Integer customerId){
		List<OrderShow> allOrders=orderService.getAllOrders(customerId);
		return SysResult.build(200, "ok", allOrders);
	}
	
	//删除订单
	@RequestMapping("/order/manage/deleteOrder")
	public SysResult deleteOrder(Integer orderId, Integer customerId){
		Integer a=orderService.deleteOrder(orderId);
		if(a>0){
			UserLog ur=new UserLog();
			ur.setCustomerId(customerId);
			ur.setLogInfo("您的订单取消成功！");
			String url="http://localhost:9005/zuul-customer/customer/manage/addLog?customerId="+ur.getCustomerId()+"&LogInfo="+ur.getLogInfo();
//			System.out.println(url);
			Integer ans=restTemplate.getForObject(url, Integer.class);
		    if(ans==1){
		    	System.out.println("删除成功！");
		    }
			return SysResult.build(200, "ok", a);
		}else{
			return SysResult.build(200, "ok", a);
		}
	}
	
}
