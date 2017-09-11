package cn.itcase.many2one;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcase.utils.hibernateutils;

/**
 * ����һ�Զ���ز���
 * @author Administrator
 *
 */
public class HibernateTest {
	//�ڿͻ�������inverse=��true��
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
	//�ͻ��붩�������ϵ�������Ƿ����
	//����ʱ�Ƚ�Order.hbm.xml�е�cascadeɾ��
	@Test
	public void test6(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		//�õ��ͻ�
		Customer ct=(Customer) ss.get(Customer.class,3);
		//����
		Order od=(Order) ss.get(Order.class, 5);
		//����ͻ��붩���Ĺ�ϵ
		ct.getOrders().remove(od);
		
		ts.commit();
		ss.close();
	}
	//���ü���ɾ����ɾ���ͻ�ͬʱɾ���������ڿͻ�һ��Customer.hbm.xml������cascade="delete"
	@Test
	public void test5(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Customer ct=(Customer) ss.get(Customer.class, 2);
		ss.delete(ct);
				
		ts.commit();
		ss.close();
	}
	//���ü���ɾ����ɾ������ͬʱɾ���ͻ����ڶ���һ��Order.hbm.xml������cascade="delete"
		@Test
		public void test4(){
			Session ss=hibernateutils.getSession();
			Transaction ts=ss.beginTransaction();
			
			Order od=(Order) ss.get(Order.class, 1);
			ss.delete(od);
			
			ts.commit();
			ss.close();
		}
	//���ü������������涩����ͬʱ����ͻ����ڶ���һ��Order.hbm.xml������cascade="save-update"
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
		
		//���������Ϳͻ��Ĺ�ϵ
		ct.getOrders().add(od1);
		ct.getOrders().add(od2);
		
		od1.setCus(ct);
		od2.setCus(ct);
		
		ss.save(od1);
		ss.save(od2);
	
		ts.commit();
		ss.close();
	}
	//���ü�������������ͻ���ͬʱ�������涩�����ڿͻ�һ��Customer.hbm.xml������cascade="save-update"
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
		
		//���������Ϳͻ��Ĺ�ϵ
		ct.getOrders().add(od1);
		ct.getOrders().add(od2);
		
		od1.setCus(ct);
		od2.setCus(ct);
		
		ss.save(ct);
	
		ts.commit();
		ss.close();
	}
	//�����ͻ��Ͷ���
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
		
		//���������Ϳͻ��Ĺ�ϵ
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
