/**
 * 
 */
package com.henrybear.flows;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.henrybear.bean.Passwd;
import com.henrybear.util.Context;
import com.henrybear.util.Encoder;

/**
 * @author Administrator
 *
 */
public class PasswdStep extends Step {

	@Override
	public void executor(Context context) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		Logger log = Logger.getLogger("step");
		Map map = (Map) context.getContext("param");
		Set<String> set = map.keySet();
		Passwd passwd = new Passwd();
		for(String key:set){
			BeanUtils.setProperty(passwd, key, map.get(key));
		}
		String pwd = passwd.getPasswd();
		pwd = Encoder.enCoder(pwd, "MD5");
		passwd.setPasswd(pwd);
		SqlSession sql = (SqlSession) context.getContext("sqlSession");
		
		sql.insert("henrybearMapper.insert_passwd", passwd);
		sql.commit();
		
	}

}
