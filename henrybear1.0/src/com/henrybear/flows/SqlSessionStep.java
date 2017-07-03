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
		log.info("�������ݿ�...");
		try {
			sqlSession = MybatisFactory.getSqlSession(path);
			context.setContext("sqlSession", sqlSession);
			log.info("���ݿ����ӳɹ�");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}

}
