/**
 * 
 */
package com.henrybear.flows;

import java.io.FileNotFoundException;

import org.apache.ibatis.session.SqlSession;

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
		SqlSession sqlSession = null;
		String path = (String) context.getContext("APPpath");
		System.out.println("连接数据库...");
		try {
			sqlSession = MybatisFactory.getSqlSession(path);
			context.setContext("sqlSession", sqlSession);
			System.out.println("数据库连接成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
