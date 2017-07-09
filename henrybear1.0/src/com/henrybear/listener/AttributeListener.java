package com.henrybear.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

public class AttributeListener implements HttpSessionAttributeListener,
		ServletRequestAttributeListener, ServletContextAttributeListener {
	private Logger log = Logger.getLogger("listener");

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext servletContext = arg0.getServletContext();
		log.info("context新增"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		log.info("context移除"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		log.info("context修改"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		log.info("Request新增"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		log.info("Request移除"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		log.info("Request修改"+arg0.getName()+":"+arg0.getValue());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		String name = arg0.getName();
		log.info("新增ID："+session.getId()+" 的session属性"+name+",值是："+arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		String name = arg0.getName();
		log.info("ID为"+session.getId()+"的session被移除的属性"+name+",值是："+arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		String name = arg0.getName();
		log.info("ID为"+session.getId()+"的session被修改的属性"+name+",值是："+arg0.getValue());
	}

}
