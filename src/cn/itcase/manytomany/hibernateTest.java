package cn.itcase.manytomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcase.utils.hibernateutils;

public class hibernateTest {
	//����
	@Test
	public void test0(){
		Session ss=hibernateutils.getSession();
		ss.close();
	}
	//���Ա���
	@Test
	public void test1(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Student s1=new Student();
		s1.setSname("aly");
		Student s2=new Student();
		s2.setSname("lay");
		
		Course c1=new Course();
		c1.setCname("java���");
		Course c2=new Course();
		c2.setCname("ios");
		
		s1.getCourses().add(c1);
		//c1.getStu().add(s1);������˫�������������������ͻ
		s2.getCourses().add(c2);
		
		ss.save(s1);
		ss.save(s2);
		ss.save(c1);
		ss.save(c2);
		
		ts.commit();
		ss.close();
	}
	//���ѧ���Ϳγ̹�����ϵ
	@Test
	public void test2(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Student s=(Student) ss.get(Student.class, 1);
		Course c=(Course) ss.get(Course.class,1);
		
		s.getCourses().remove(c);
		//c.getStu().remove(s);����д�����ܽ����ϵ
		ts.commit();
		ss.close();
	}
	//�ı�ѧ���Ϳγ̹�����ϵ��ԭ��2��ѧ��ѡ2�ſγ̣���Ϊ��2��ѧ��ѡ1�ſγ̣�
	@Test
	public void test3(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
			
		Student s=(Student) ss.get(Student.class,2);
		Course c1=(Course) ss.get(Course.class,2);
		Course c2=(Course) ss.get(Course.class,1);
			
		s.getCourses().remove(c1);//�����ϵ
		s.getCourses().add(c2);//������ϵ
			
		ts.commit();
		ss.close();
	}
	//ɾ��ѧ��ͬʱ���Զ�ɾ��ѡ�μ�¼�����Զ�Զ಻��Ҫ����
	@Test
	public void test4(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
			
		Student s=(Student) ss.get(Student.class,2);
		
		ss.delete(s);
			
		ts.commit();
		ss.close();
	}
}
