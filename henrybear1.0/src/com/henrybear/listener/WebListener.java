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
		log.info(request.getRemoteAddr() +	"请求处理结束，用时：" + time + " ms。");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		String uri = request.getRequestURI();
		uri = request.getQueryString() == null ? uri : (uri+"?"+request.getQueryString());
		request.setAttribute("dateCreated", System.currentTimeMillis());
		log.info("IP:"+request.getRemoteAddr()+"请求"+uri);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
//		log.info("即将关闭 "+servletContext.getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		log.info("即将启动 "+servletContext);
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		log.info("新创建一个session,ID: "+session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		log.info("新销毁一个session,ID: "+session.getId());
	}

}
