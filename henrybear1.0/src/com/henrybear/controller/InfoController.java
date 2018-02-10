package com.henrybear.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.henrybear.DB.MybatisFactory;
import com.henrybear.bean.MessageBean;
import com.henrybear.bean.RegInfo;

@Controller
@RequestMapping("/info")
public class InfoController {
	private static final String SUCCESS = "success";
	private static SqlSession sqlSession = null;
	private static Logger log = Logger.getLogger("spring");
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String reregister(HttpServletRequest request) {
		String webroot = request.getSession().getServletContext().getRealPath("/");
		System.out.println(webroot);
		try {
			sqlSession = MybatisFactory.getSqlSession(webroot);
			log.info(sqlSession+" 会话已打开");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String relogin(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(@RequestBody RegInfo info, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(info);
		if(sqlSession == null) {
			String webroot = request.getSession().getServletContext().getRealPath("/");
			log.debug(webroot);
			try {
				sqlSession = MybatisFactory.getSqlSession(webroot);
				log.info(sqlSession+" 会话已打开");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<RegInfo> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("先查询帐号为"+info.getAccount()+"的记录");
			list = sqlSession.selectList("henrybearMapper.selectlist", info);
			if(list.size() == 0) {
				msg.setFlag(false);
				msg.setRetcode(121);
				msg.setRetMsg(info.getAccount()+"用户不存在");
			}else if(list.size() > 1) {
				msg.setFlag(false);
				msg.setRetcode(122);
				msg.setRetMsg(info.getAccount()+"存在多条记录");
			}else {
				if(info.getPassword().equals(list.get(0).getPassword())) {
					msg.setFlag(true);
					msg.setRetcode(0);
					msg.setRetMsg(info.getAccount()+"|登录成功");
					Cookie cookie = new Cookie("account", info.getAccount());
					response.addCookie(cookie);
				}else {
					msg.setFlag(false);
					msg.setRetcode(999);
					msg.setRetMsg("帐号或密码错误！");
				}
			}
		}catch(Exception e) {
			log.error(e);
		}
		response.setContentType("text/html;charset=UTF-8");
    	response.getWriter().println(JSONObject.toJSONString(msg));
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public void register(@RequestBody RegInfo info, HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println(info);
		if(sqlSession == null) {
			String webroot = request.getSession().getServletContext().getRealPath("/");
			log.debug(webroot);
			try {
				sqlSession = MybatisFactory.getSqlSession(webroot);
				log.info(sqlSession+" 会话已打开");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<RegInfo> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("先查询帐号为"+info.getAccount()+"的记录");
			list = sqlSession.selectList("henrybearMapper.selectlist", info);
			if(list.size() > 0) {
				log.error(info+"已经存在");
				log.error(info+" insert 失败");
				msg.setFlag(false);
				msg.setRetcode(110);
				msg.setRetMsg(info.getAccount()+"已经存在");
			}else {
				info.setRegdate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
				log.debug("准备插入数据库");
				int ret = sqlSession.insert("henrybearMapper.insert_reginfo", info);
				sqlSession.commit();
				System.out.println(ret);
				if(ret == 1) {
					log.info(info+" insert 成功");
					msg.setFlag(true);
					msg.setRetcode(0);
					msg.setRetMsg(info.getAccount());
				}else {
					log.error(info+" insert 失败");
					msg.setFlag(false);
					msg.setRetcode(120);
					msg.setRetMsg("insert 失败");
				}
			}
		}catch(Exception e) {
			log.error(e);
		}
		response.setContentType("text/html;charset=UTF-8");
    	response.getWriter().println(JSONObject.toJSONString(msg));
	}
}
