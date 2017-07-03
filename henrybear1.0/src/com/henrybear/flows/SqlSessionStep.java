/**
 * 
 */
package com.henrybear.flows;

import java.io.FileNotFoundException;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.henrybear.DB.MybatisFactory;
import com.henrybear.util.Context;

/**
 * @author Administrator
 *
 */
public class SqlSessionStep extends Step {

	@Override
	public void executor(Context context) {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger("step");
		SqlSession sqlSession = null;
		String path = (String) context.getContext("APPpath");
		log.info("连接数据库...");
		try {
			sqlSession = MybatisFactory.getSqlSession(path);
			context.setContext("sqlSession", sqlSession);
			log.info("数据库连接成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}

}
