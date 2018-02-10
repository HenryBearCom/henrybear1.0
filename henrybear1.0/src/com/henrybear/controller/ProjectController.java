package com.henrybear.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.henrybear.DB.MybatisFactory;
import com.henrybear.bean.MessageBean;
import com.henrybear.bean.ProjectBean;
import com.henrybear.bean.RegInfo;

@Controller
@RequestMapping("/project")
public class ProjectController {

	private static SqlSession sqlSession = null;
	private static Logger log = Logger.getLogger("spring");
	
	@ModelAttribute
	public void init(HttpServletRequest request) {
		if(sqlSession == null) {
			String webroot = request.getSession().getServletContext().getRealPath("/");
			log.debug(webroot);
			try {
				sqlSession = MybatisFactory.getSqlSession(webroot);
				log.info(sqlSession+" �Ự�Ѵ�");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String reAdd(HttpServletRequest request) {
		return "projectadd";
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String reSearch(HttpServletRequest request) {
		return "projectsearch";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void add(@RequestBody ProjectBean project,HttpServletResponse response) throws IOException {
		List<ProjectBean> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("��ѯ���Ϊ"+project.getSerialname()+"�ļ�¼");
			list = sqlSession.selectList("henrybearMapper.select_projectlist", project);
			if(list.size() > 0) {
				log.error(project+"�Ѿ�����");
				log.error(project+" insert ʧ��");
				msg.setFlag(false);
				msg.setRetcode(110);
				msg.setRetMsg(project.getSerialname()+"�Ѿ�����");
			}else {
				project.setBak(new SimpleDateFormat("yyyyMMdd").format(new Date()));
				log.debug("׼���������ݿ�");
				int ret = sqlSession.insert("henrybearMapper.insert_projectmanage", project);
				sqlSession.commit();
				System.out.println(ret);
				if(ret == 1) {
					log.info(project+" insert �ɹ�");
					msg.setFlag(true);
					msg.setRetcode(0);
					msg.setRetMsg(project.getSerialname());
				}else {
					log.error(project+" insert ʧ��");
					msg.setFlag(false);
					msg.setRetcode(120);
					msg.setRetMsg("insert ʧ��");
				}
			}
		}catch(Exception e) {
			log.error(e);
			log.error(project+" insert ʧ��");
			msg.setFlag(false);
			msg.setRetcode(120);
			msg.setRetMsg(e.getMessage());
		}
		response.setContentType("text/html;charset=UTF-8");
    	response.getWriter().println(JSONObject.toJSONString(msg));
	}
	

	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestBody ProjectBean project,HttpServletResponse response) {
		List<ProjectBean> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("��ѯ���Ϊ"+project.getSerialname()+"�ļ�¼");
			list = sqlSession.selectList("henrybearMapper.select_projectlist", project);
			if(list.size() == 0) {
				log.error(project+"������");
				msg.setFlag(false);
				msg.setRetcode(121);
				msg.setRetMsg(project.getSerialname()+"������");
				return null;
			}else {
				return list;
			}
		}catch(Exception e) {
			log.error(e);
			return null;
		}
	}
	
	@RequestMapping(value="/mod",method=RequestMethod.GET)
	public String mod(@RequestParam String q, Model model) throws IOException {

		System.out.println(q);
		ProjectBean project = new ProjectBean();
		project.setSerial(q);
		List<ProjectBean> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("��ѯ���Ϊ"+project.getSerialname()+"�ļ�¼");
			list = sqlSession.selectList("henrybearMapper.select_projectlist", project);
			if(list.size() == 0) {
				log.error(project+"������");
				msg.setFlag(false);
				msg.setRetcode(121);
				msg.setRetMsg(project.getSerialname()+"������");
				return "projectsearch";
			}else if(list.size() > 1)  {
				log.error(project+"��"+list.size()+"��");
				msg.setFlag(false);
				msg.setRetcode(122);
				msg.setRetMsg(project.getSerialname()+"�ж���");
				return "projectsearch";
			}else {
				model.addAttribute("modproj", list.get(0));
				System.out.println(list.get(0).toJson());
				return "modproject";
			}
		}catch(Exception e) {
			log.error(e);
			return "projectsearch";
		}
	}
	@RequestMapping(value="/mod",method=RequestMethod.POST)
	public void modUpdate(@RequestBody ProjectBean project, Model model, HttpServletResponse response) throws IOException {
		log.debug(project.toJson());
		
		MessageBean msg = new MessageBean();
		try {
			log.debug("�������Ϊ"+project.getSerialname()+"�ļ�¼");
			int ret = sqlSession.update("henrybearMapper.update_projectmanage", project);
			if(ret == 1) {
				sqlSession.commit();
				log.info(project.toJson()+"���³ɹ�");
				msg.setFlag(true);
				msg.setRetcode(0);
				msg.setRetMsg(project.getSerialname()+"���³ɹ�");
			}else{
				sqlSession.rollback();
				log.error(project.toJson()+"����ʧ��");
				msg.setFlag(false);
				msg.setRetcode(124);
				msg.setRetMsg(project.getSerialname()+"����ʧ��");
			}
		}catch(Exception e) {
			log.error(e);
			msg.setFlag(false);
			msg.setRetcode(124);
			msg.setRetMsg(project.getSerialname()+"����ʧ��");
		}finally {

	    	response.getWriter().println(JSONObject.toJSONString(msg));
		}
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public String del(@RequestParam String q, Model model) throws IOException {

		System.out.println(q);
		ProjectBean project = new ProjectBean();
		project.setSerial(q);
		List<ProjectBean> list = null;
		MessageBean msg = new MessageBean();
		try {
			log.debug("��ѯ���Ϊ"+project.getSerialname()+"�ļ�¼");
			list = sqlSession.selectList("henrybearMapper.select_projectlist", project);
			if(list.size() == 0) {
				log.error(project+"������");
				msg.setFlag(false);
				msg.setRetcode(121);
				msg.setRetMsg(project.getSerialname()+"������");
				return "projectsearch";
			}else if(list.size() > 1)  {
				log.error(project+"��"+list.size()+"��");
				msg.setFlag(false);
				msg.setRetcode(122);
				msg.setRetMsg(project.getSerialname()+"�ж���");
				return "projectsearch";
			}else {
				model.addAttribute("modproj", list.get(0));
				System.out.println(list.get(0).toJson());
				return "delproject";
			}
		}catch(Exception e) {
			log.error(e);
			return "projectsearch";
		}
	}
	
}
