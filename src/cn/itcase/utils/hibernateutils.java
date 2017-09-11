package cn.itcase.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateutils {
	private static Configuration conf;
	private static SessionFactory sf;
	static{
		conf=new Configuration().configure();
		sf=conf.buildSessionFactory();
	}
	public static Session getSession(){
		return sf.openSession();
	}
}
