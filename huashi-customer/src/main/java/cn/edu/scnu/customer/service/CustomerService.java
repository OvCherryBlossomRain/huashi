package cn.edu.scnu.customer.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisPool;
import cn.edu.scnu.customer.mapper.CustomerMapper;
import cn.edu.scnu.pojo.Customer;
import cn.edu.scnu.pojo.UserLog;
import cn.edu.scnu.utils.MapperUtil;
import cn.edu.scnu.utils.PrefixKey;

@Service
public class CustomerService {
	@Autowired
	private CustomerMapper customermapper;
	@Autowired
	private JedisCluster jedis;
//	@Autowired
//	private ShardedJedisPool pool;
	private ObjectMapper mapper = MapperUtil.MP;
	
	
	public Integer checkEmail(String email) {
		return customermapper.queryEmail(email);
	}


	public void saveCustomer(Customer customer) {
		customer.setType(2);
		System.out.println("service1");
		customermapper.saveCustomer(customer);
		Customer exist = customermapper.queryUserByEmailAndPassword(customer);
		UserLog ur=new UserLog();
		ur.setCustomerId(exist.getCustomerId());
		ur.setLogInfo("恭喜你成为会员用户");
		customermapper.saveLog(ur);
		System.out.println("插入Log成功");
	}


	public String doLogin(Customer customer) {
//		ShardedJedis jedis = pool.getResource();
		try {
			Customer exist = customermapper.queryUserByEmailAndPassword(customer);
			if (exist == null)
				return "";
			else {
//				String ticket = UUID.randomUUID().toString();
				String ticket=PrefixKey.CUSTOMER_LOGIN_TICKET+System.currentTimeMillis()+exist.getCustomerId();
				String userJson;
				userJson = mapper.writeValueAsString(exist);

				//判断当前用户是否曾经有人登录过
				String existTicket=jedis.get(PrefixKey.CUSTOMER_LOGINED_CHECK_PREFIX+exist.getCustomerId());
				//顶替实现.不允许前一个登录的人ticket存在 单人
				if(StringUtils.isNotEmpty(existTicket)){
					jedis.del(existTicket);
				}
				//定义当前客户端登录的信息 userId:ticket
				jedis.setex(PrefixKey.CUSTOMER_LOGINED_CHECK_PREFIX+exist.getCustomerId(), 60*30,ticket);
				jedis.setex(ticket,60*30, userJson);
				
				return ticket;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
//		finally {
//			pool.returnResource(jedis);
//		}
	}


	public String queryCustomerJson(String ticket) {
//		ShardedJedis jedis = pool.getResource();
		String userJson="";
		try {
			Long lefttime=jedis.pttl(ticket);
			if(lefttime<1000*60*10l){
				jedis.pexpire(ticket, lefttime+1000*60*5);
			}
			userJson=jedis.get(ticket);
			return userJson;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
//		finally {
//			pool.returnResource(jedis);
//		}
	}


	public List<UserLog> queryCustomerLogJson(Integer customerId) {
		
		return customermapper.queryCustomerLogJson(customerId);
	}


	public String updateCustomerInfo(Customer customer) {
		try{
			customermapper.updateCustomerInfo(customer);
//			ShardedJedis jedis = pool.getResource();
			String ticket = UUID.randomUUID().toString();
			String userJson="";
			userJson = mapper.writeValueAsString(customer);
			jedis.set(ticket, userJson);
			return ticket;
		}catch (Exception e) {
			return "";
		}
	}


	public Integer updatePassword(Customer customer) {
		try {
			customermapper.updatePassword(customer);
			UserLog ur=new UserLog();
			ur.setCustomerId(customer.getCustomerId());
			ur.setLogInfo("您已修改成功密码！");
			customermapper.saveLog(ur);
			System.out.println("插入Log成功");
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}


	public Integer loginout(String ticket) {
//		ShardedJedis jedis = pool.getResource();
		try {
			jedis.del(ticket);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}


	public void addLog(UserLog userlog) {
		// TODO Auto-generated method stub
		System.out.println(userlog.getCustomerId()+userlog.getLogInfo());
		customermapper.saveLog(userlog);
	}
}
