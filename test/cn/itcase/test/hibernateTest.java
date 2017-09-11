package cn.itcase.test;



import org.hibernate.FlushMode;
import org.hibernate.Query;

/**
 * �־û����������״̬��session��һ���������
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


import cn.itcase.daomain.User;
import cn.itcase.utils.hibernateutils;

public class hibernateTest {
	
	//��ʼ������
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
	//���Ի���Ĵ���
		@Test
		public void Test2(){
			Session ss=hibernateutils.getSession();
			Transaction ts=ss.beginTransaction();
			//��ó־ö��󣬳־ö�����һ������������
			User cs=(User) ss.get(User.class,1);
			User cs2=(User) ss.get(User.class,1);
			System.out.println("user:"+cs);
			System.out.println("user2:"+cs2);
			ts.commit();
			ss.close();		
		}
	//�־ö���ӵ���Զ��������ݿ������
	@Test
	public void test3(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//��ó־ö���
		User cs=(User)ss.get(User.class, 1);
		cs.setName("lisi");
		ts.commit();
		ss.close();
	}
	//session��ʱˢ������
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
		
		//3.�����ύʱ		
		ts.commit();
		ss.close();
	}
	//�����������ķ���clear��evict
	@Test
	public void test5(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User cs=(User) ss.get(User.class, 1);
		//�������һ�������е�����
		//ss.clear();
		//���ָ��������һ�������е�����
		ss.evict(cs);
		User cs1=(User) ss.get(User.class, 1);
		ts.commit();
		ss.close();
	}
	//����ˢ��һ������----refresh
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
	//����flushMode
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
	//�����־û�����ķ���:save
	@Test
	public void test8(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//˲ʱ����
		User user=new User();
		user.setName("itcast");
		//save����ʹ˲ʱ�����Ϊ�־ö���
		ss.save(user);
		ts.commit();
		ss.close();
	}
	//�����־û�����ķ�����update
	@Test
	public void test9(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//˲ʱ����
		User user=new User();
		//�йܶ���
		user.setId(1);
		user.setName("itcas");
		//update�������йܶ����Ϊ�־ö���
		ss.update(user);
		ts.commit();
		ss.close();
	}
	//��update()��������һ���йܶ���ʱ�������Session�Ļ������Ѿ�������ͬOID�ĳ־ö��󣬻��׳��쳣
	@Test
	public void test10(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		User user=(User) ss.get(User.class, 1);
		User user2=new User();
		user2.setId(1);//�йܶ���
		user2.setName("abc");
		ss.update(user2);//user2Ҳ��Ϊ�־ö���
		ts.commit();
		ss.close();
	}
	//saveorUpdate:������Ϊ˲ʱ����ʱִ��save����������Ϊ�йܶ���ʱִ��update����
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
	//����get������load����
	@Test
	public void test12(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		//get������ѯ�������ݷ���null
		//User user=(User) ss.get(User.class, 100);
		//load������ѯ���������׳�ObjectNotFound�쳣
		User user=(User) ss.load(User.class, 100);
		System.out.println(user);
		ts.commit();
		ss.close();
	}
	//�����־ö�Ϸ��delete
	@Test
	public void test13(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		User user=new User();
		user.setId(1);//�йܶ���
		
		User user2=(User) ss.get(User.class, 2);//�־ö���
		//ss.delete(user);
		ss.delete(user2);
		ts.commit();
		ss.close();
	}
}

