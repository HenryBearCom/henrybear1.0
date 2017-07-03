/**
 * 用于返回SqlSession类
 */
package com.henrybear.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 *
 */
public class MybatisFactory {
	
	private static Logger log = Logger.getLogger("mybatis");
	
	public static SqlSession getSqlSession(String APPpath) throws FileNotFoundException{
		log.info("mybatis框架启动...");
		String resource = APPpath + "/config/mybatis-config.xml";
		
		InputStream inputStream = new FileInputStream(new File(resource));
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sql = sqlSessionFactory.openSession();
		return sql;
	}
	
	public static SqlSession getSqlSession(String APPpath,String env) throws FileNotFoundException{
		log.info("mybatis框架启动...");
		String resource = APPpath + "/config/mybatis-config.xml";
		InputStream inputStream = new FileInputStream(new File(resource));
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, env);
		SqlSession sql = sqlSessionFactory.openSession();
		return sql;
	}

	public static void main(String[] args) throws IOException{
		String resource = "./config/mybatis-config.xml";
		InputStream inputStream = new FileInputStream(new File(resource));
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sql = sqlSessionFactory.openSession();
	}
}
