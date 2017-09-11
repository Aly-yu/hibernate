package cn.itcase.manytomany;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private Integer id;
	private String sname;
	//一个学生选择多门课程
	private Set<Course> courses=new HashSet<Course>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
}
