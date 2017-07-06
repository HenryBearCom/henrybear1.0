package com.henrybear.flows;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.henrybear.bean.RegInfo;
import com.henrybear.util.Context;

public class RegInfoStep extends Step {

	@Override
	public void executor(Context context) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger("step");
		Map map = (Map) context.getContext("param");
		Set<String> set = map.keySet();
		RegInfo reginfo = new RegInfo();
		for(String key:set){
			BeanUtils.setProperty(reginfo, key, map.get(key));
		}
		SqlSession sql = (SqlSession) context.getContext("sqlSession");
		
		sql.insert("henrybearMapper.insert_reginfo", reginfo);
		sql.commit();
		sql.close();
	}

}
