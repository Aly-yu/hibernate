package cn.itcase.many2one;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcase.utils.hibernateutils;

/**
 * 测试一对多相关操作
 * @author Administrator
 *
 */
public class HibernateTest {
	//在客户端配置inverse=“true”
	@Test
	public void test7(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=new Customer();
		ct.setName("jack");
		
		Order od=new Order();
		od.setOrderNumber("0005");
		od.setPrice(100d);
		
		ct.getOrders().add(od);
		ss.save(ct);
		
		ts.commit();
		ss.close();
	}
	//客户与订单解除关系，订单是否存在
	//运行时先将Order.hbm.xml中的cascade删除
	@Test
	public void test6(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		//得到客户
		Customer ct=(Customer) ss.get(Customer.class,3);
		//订单
		Order od=(Order) ss.get(Order.class, 5);
		//解除客户与订单的关系
		ct.getOrders().remove(od);
		
		ts.commit();
		ss.close();
	}
	//设置级联删除，删除客户同时删除订单，在客户一方Customer.hbm.xml中配置cascade="delete"
	@Test
	public void test5(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=(Customer) ss.get(Customer.class, 2);
		ss.delete(ct);
				
		ts.commit();
		ss.close();
	}
	//设置级联删除，删除订单同时删除客户，在订单一方Order.hbm.xml中配置cascade="delete"
		@Test
		public void test4(){
			Session ss=hibernateutils.getSession();
			Transaction ts=ss.beginTransaction();
			
			Order od=(Order) ss.get(Order.class, 1);
			ss.delete(od);
			
			ts.commit();
			ss.close();
		}
	//设置级联操作，保存订单的同时保存客户，在订单一方Order.hbm.xml中设置cascade="save-update"
	@Test
	public void test3(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=new Customer();
		ct.setName("lisi");
		
		Order od1=new Order();
		od1.setOrderNumber("0001");
		od1.setPrice(100.0);
		
		Order od2=new Order();
		od2.setOrderNumber("0002");
		od2.setPrice(200.0);
		
		//描述订单和客户的关系
		ct.getOrders().add(od1);
		ct.getOrders().add(od2);
		
		od1.setCus(ct);
		od2.setCus(ct);
		
		ss.save(od1);
		ss.save(od2);
	
		ts.commit();
		ss.close();
	}
	//设置级联操作，保存客户的同时级联保存订单，在客户一方Customer.hbm.xml中配置cascade="save-update"
	@Test
	public void test2(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=new Customer();
		ct.setName("wangwu");
		
		Order od1=new Order();
		od1.setOrderNumber("0001");
		od1.setPrice(100.0);
		
		Order od2=new Order();
		od2.setOrderNumber("0002");
		od2.setPrice(200.0);
		
		//描述订单和客户的关系
		ct.getOrders().add(od1);
		ct.getOrders().add(od2);
		
		od1.setCus(ct);
		od2.setCus(ct);
		
		ss.save(ct);
	
		ts.commit();
		ss.close();
	}
	//保护客户和订单
	@Test
	public void test1(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=new Customer();
		ct.setName("zhangsan");
		
		Order od1=new Order();
		od1.setOrderNumber("0001");
		od1.setPrice(100.0);
		
		Order od2=new Order();
		od2.setOrderNumber("0002");
		od2.setPrice(200.0);
		
		//描述订单和客户的关系
		ct.getOrders().add(od1);
		ct.getOrders().add(od2);
		
		od1.setCus(ct);
		od2.setCus(ct);
		
		ss.save(ct);
		ss.save(od1);
		ss.save(od2);
		ts.commit();
		ss.close();
	}
}
