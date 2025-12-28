package cn.edu.scnu.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.scnu.pojo.HallPojo;
import cn.edu.scnu.pojo.Order;
import cn.edu.scnu.pojo.SeatOrder;
import cn.edu.scnu.pojo.SeatPojo;
import cn.edu.scnu.pojo.SessionPojo;

@Repository
public interface OrderMapper {

	public List<HallPojo> getSessions();

	public List<SessionPojo> getSessionsByMid(Integer movieId);

	public List<SeatPojo> getSeats(Integer sessionId);

	public List<SessionPojo> getSessionTicketMoney(Integer sessionId);

	public void insertSeatOrder(SeatOrder seatOrder);

	public Order getMaxOrder();

	public SessionPojo getSessionsBySid(Integer sessionId);

	public void addNewOrder(Order newOrder);
	//新

	public List<Order> getOrders(Integer customerId);

	public List<Integer> getSeatId(Integer orderId);

	public Integer getSessionId(Integer orderId);

	public String getMovieDes(Integer hallId);

	public String getMovieName(Integer movieId);

	public SeatPojo getSeatsInfo(Integer seatId);

	public Integer delOrder(Integer orderId);

	public Integer delOrderandseat(Integer orderId);

}
