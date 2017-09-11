package cn.itcase.manytomany;

import java.util.HashSet;
import java.util.Set;

public class Course {
	private Integer id;
	private String cname;
	//一门课程被多个学生选修
	private Set<Student> stu=new HashSet<Student>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Student> getStu() {
		return stu;
	}
	public void setStu(Set<Student> stu) {
		this.stu = stu;
	}
	
}
