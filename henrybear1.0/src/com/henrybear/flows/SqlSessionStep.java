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
		System.out.println("�������ݿ�...");
		try {
			sqlSession = MybatisFactory.getSqlSession();
			context.setContext("sqlSession", sqlSession);
			System.out.println("���ݿ����ӳɹ�");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
