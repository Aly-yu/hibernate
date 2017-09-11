package cn.itcase.test;



import org.hibernate.FlushMode;
import org.hibernate.Query;

/**
 * 持久化对象的三种状态和session的一级缓存测试
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


import cn.itcase.daomain.User;
import cn.itcase.utils.hibernateutils;

public class hibernateTest {
	
	//初始化数据
	@Test
	public void test1(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		for(int i=0;i<10;i++){
			User us=new User();
			us.setName("user"+i);
			ss.save(us);
		}
		ts.commit();
		ss.close();
	}
	//测试缓存的存在
		@Test
		public void Test2(){
			Session ss=hibernateutils.getSession();
			Transaction ts=ss.beginTransaction();
			//获得持久对象，持久对象在一级缓存中引用
			User cs=(User) ss.get(User.class,1);
			User cs2=(User) ss.get(User.class,1);
			System.out.println("user:"+cs);
			System.out.println("user2:"+cs2);
			ts.commit();
			ss.close();		
		}
	//持久对象拥有自动更新数据库的能力
	@Test
	public void test3(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//获得持久对象
		User cs=(User)ss.get(User.class, 1);
		cs.setName("lisi");
		ts.commit();
		ss.close();
	}
	//session何时刷出缓存
	@Test
	public void test4(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User cs=(User)ss.get(User.class, 1);
		cs.setName("zahngsan");
		//1.query.list();
		/*Query query=ss.createQuery("from User");
		query.list();*/
		//2.session.flush();
		//ss.flush();
		
		//3.事务提交时		
		ts.commit();
		ss.close();
	}
	//测试清除缓存的方法clear，evict
	@Test
	public void test5(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User cs=(User) ss.get(User.class, 1);
		//清除所有一级缓存中的数据
		//ss.clear();
		//清除指定对象在一级缓存中的引用
		ss.evict(cs);
		User cs1=(User) ss.get(User.class, 1);
		ts.commit();
		ss.close();
	}
	//测试刷新一级缓存----refresh
	@Test
	public void test6(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User cs=(User) ss.get(User.class, 1);
		cs.setName("wangwu");
		ss.refresh(cs);
		System.out.println(cs.getName());
		ts.commit();
		ss.close();
	}
	//测试flushMode
	@Test
	public void test7(){
		Session ss=hibernateutils.getSession();
		//ss.setFlushMode(FlushMode.MANUAL);
		Transaction ts=ss.beginTransaction();
		User cs=(User) ss.get(User.class, 1);
		cs.setName("wangwu");
		Query query=ss.createQuery("from User");
		query.list();
		//ss.flush();
		ts.commit();
		ss.close();
	}
	//操作持久化对象的方法:save
	@Test
	public void test8(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//瞬时对象
		User user=new User();
		user.setName("itcast");
		//save方法使瞬时对象变为持久对象
		ss.save(user);
		ts.commit();
		ss.close();
	}
	//操作持久化对象的方法：update
	@Test
	public void test9(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//瞬时对象
		User user=new User();
		//托管对象
		user.setId(1);
		user.setName("itcas");
		//update方法是托管对象变为持久对象
		ss.update(user);
		ts.commit();
		ss.close();
	}
	//当update()方法关联一个托管对象时，如果在Session的缓存中已经存在相同OID的持久对象，会抛出异常
	@Test
	public void test10(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User user=(User) ss.get(User.class, 1);
		User user2=new User();
		user2.setId(1);//托管对象
		user2.setName("abc");
		ss.update(user2);//user2也变为持久对象
		ts.commit();
		ss.close();
	}
	//saveorUpdate:当参数为瞬时对象时执行save操作，参数为托管对象时执行update操作
	@Test
	public void test11(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//User user=new User();
		//user.setName("ddd");
		/*user.setId(1);
		user.setName("aa");*/
		User user=(User) ss.get(User.class, 1);
		user.setName("www");
		ss.saveOrUpdate(user);
		ts.commit();
		ss.close();
	}
	//测试get方法和load方法
	@Test
	public void test12(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//get方法查询不到数据返回null
		//User user=(User) ss.get(User.class, 100);
		//load方法查询不到数据抛出ObjectNotFound异常
		User user=(User) ss.load(User.class, 100);
		System.out.println(user);
		ts.commit();
		ss.close();
	}
	//操作持久对戏：delete
	@Test
	public void test13(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		User user=new User();
		user.setId(1);//托管对象
		
		User user2=(User) ss.get(User.class, 2);//持久对象
		//ss.delete(user);
		ss.delete(user2);
		ts.commit();
		ss.close();
	}
}

