package com.henrybear.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.henrybear.flows.DoFlows;
import com.henrybear.util.Context;

public class HttpServiceHttp extends HttpServlet {

	private Logger log = Logger.getLogger("servlet");
	
	/**
	 * Constructor of the object.
	 */
	public HttpServiceHttp() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
		log.debug(response.getCharacterEncoding()+","+request.getCharacterEncoding());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map param = new HashMap();
		String flag = request.getParameter("flag");
		Context context = new Context();
		String path = request.getSession().getServletContext().getRealPath("/");
		context.setContext("APPpath", path);
		
		String account = request.getParameter("account");
		try {
			if(!"reg".equals(flag.trim())){
				param.put("account", account);
				param.put("name", request.getParameter("name"));
				param.put("sex", request.getParameter("sex"));
				param.put("idcard", request.getParameter("idcard"));
				context.setContext("param", param);
				DoFlows.doFlows(context, new File(path+"/config/reginfo.xml"));
				Cookie cookie = new Cookie("account",account);
				cookie.setPath("/");
				response.addCookie(cookie);
				out.print("/reg.jsp");
			}else{
				param.put("passwd", request.getParameter("pwd"));
				param.put("account", account);
				context.setContext("param", param);
				DoFlows.doFlows(context, new File(path+"/config/regflow.xml"));
				Cookie cookie = new Cookie("account","");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				out.println("success");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e);
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
