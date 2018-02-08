package com.henrybear.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class WebListener implements HttpSessionListener,
		ServletContextListener, ServletRequestListener {
	
	private Logger log = Logger.getLogger("listener");

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		long time = System.currentTimeMillis() - (Long)request.getAttribute("dateCreated");
		log.info(request.getRemoteAddr() +	"�������������ʱ��" + time + " ms��");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		String uri = request.getRequestURI();
		uri = request.getQueryString() == null ? uri : (uri+"?"+request.getQueryString());
		request.setAttribute("dateCreated", System.currentTimeMillis());
		log.info("IP:"+request.getRemoteAddr()+"����"+uri);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
//		log.info("�����ر� "+servletContext.getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		log.info("�������� "+servletContext);
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		log.info("�´���һ��session,ID: "+session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		log.info("������һ��session,ID: "+session.getId());
	}

}
