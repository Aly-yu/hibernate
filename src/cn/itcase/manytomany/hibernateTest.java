package cn.itcase.manytomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcase.utils.hibernateutils;

public class hibernateTest {
	//建表
	@Test
	public void test0(){
		Session ss=hibernateutils.getSession();
		ss.close();
	}
	//测试保存
	@Test
	public void test1(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Student s1=new Student();
		s1.setSname("aly");
		Student s2=new Student();
		s2.setSname("lay");
		
		Course c1=new Course();
		c1.setCname("java编程");
		Course c2=new Course();
		c2.setCname("ios");
		
		s1.getCourses().add(c1);
		//c1.getStu().add(s1);不可以双方关联，会造成主键冲突
		s2.getCourses().add(c2);
		
		ss.save(s1);
		ss.save(s2);
		ss.save(c1);
		ss.save(c2);
		
		ts.commit();
		ss.close();
	}
	//解除学生和课程关联关系
	@Test
	public void test2(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
		
		Student s=(Student) ss.get(Student.class, 1);
		Course c=(Course) ss.get(Course.class,1);
		
		s.getCourses().remove(c);
		//c.getStu().remove(s);两种写法都能解除关系
		ts.commit();
		ss.close();
	}
	//改变学生和课程关联关系（原：2号学生选2号课程，改为：2号学生选1号课程）
	@Test
	public void test3(){
		Session ss=hibernateutils.getSession();
		Transaction ts=ss.beginTransaction();
			
		Student s=(Student) ss.get(Student.class,2);
		Course c1=(Course) ss.get(Course.class,2);
		Course c2=(Course) ss.get(Course.class,1);
			
		s.getCourses().remove(c1);//解除关系
		s.getCourses().add(c2);//建立关系
			
		ts.commit();
		ss.close();
	}
	//删除学生同时会自动删除选课记录，所以多对多不需要级联
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
