package cn.edu.scnu.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.servo.jmx.OrderedObjectNameMapper;

import cn.edu.scnu.order.mapper.OrderMapper;
import cn.edu.scnu.pojo.HallPojo;
import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.OrderShow;
import cn.edu.scnu.pojo.SeatOrder;
import cn.edu.scnu.pojo.SeatPojo;
import cn.edu.scnu.pojo.SessionPojo;
import cn.edu.scnu.pojo.UserLog;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;

	public List<HallPojo> getSessions() {
		return orderMapper.getSessions();
		
	}
	
	public List<SessionPojo> getSessionsByMid(Integer movieId) {
		
		return orderMapper.getSessionsByMid(movieId);
	}

	public List<SeatPojo> getSeats(Integer sessionId) {
		return orderMapper.getSeats(sessionId);
	}

	public List<SessionPojo> getSessionTicketMoney(Integer sessionId) {
		// TODO Auto-generated method stub
		return orderMapper.getSessionTicketMoney(sessionId);
	}

	public void insertSeatOrder(SeatOrder seatOrder) {
		orderMapper.insertSeatOrder(seatOrder);
	}

	public Order getMaxOrder() {
		return orderMapper.getMaxOrder();
	}

	public SessionPojo getSessionsBySid(Integer sessionId) {
		return orderMapper.getSessionsBySid(sessionId);
	}

	public void addNewOrder(Order newOrder) {
		orderMapper.addNewOrder(newOrder);
	}

	public List<OrderShow> getAllOrders(Integer customerId) {
		// TODO Auto-generated method stub
		List<Order> orderList=orderMapper.getOrders(customerId);
		List<OrderShow> ordershows=new ArrayList<>();
		for(int i=0;i<orderList.size();i++){
			Timestamp buyTime=orderList.get(i).getBuyTime();
			Integer money=orderList.get(i).getMoney();
			Integer hallId=orderList.get(i).getHallId();
			Integer movieId=orderList.get(i).getMovieId();
			Integer orderId=orderList.get(i).getOrderId();
			//返回所有的seat_id,在遍历seatid获取seat的详细信息，生成seatinfo
			List<Integer> seatId=orderMapper.getSeatId(orderId);
			List<SeatPojo> seatinfo=new ArrayList<>();
			for(int j=0;j<seatId.size();j++){
				 seatinfo.add(orderMapper.getSeatsInfo(seatId.get(j)));
			}
			//先根据orderid获取sessionid,再根据sessionid获取session实体类
			Integer sessionId=orderMapper.getSessionId(orderId);
			SessionPojo sessionpojo=orderMapper.getSessionsBySid(sessionId);
			//根据hallId获取大厅名称
			String description=orderMapper.getMovieDes(hallId);
			//根据movieId获取电影名称
			String movieName=orderMapper.getMovieName(movieId);
			//新建ordershow对象
			OrderShow ordershow=new OrderShow(buyTime,description,money,movieName,
					sessionpojo.getStartTime(),sessionpojo.getEndTime(),seatinfo,orderId);
			//放入集合中
			ordershows.add(ordershow);
		}
		return ordershows;
	}

	public Integer deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Integer a=orderMapper.delOrder(orderId);
		Integer b=orderMapper.delOrderandseat(orderId);
		if(a>0 && b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
}
