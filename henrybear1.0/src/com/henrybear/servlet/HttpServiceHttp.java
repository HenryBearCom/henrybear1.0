package com.henrybear.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.henrybear.flows.DoFlows;
import com.henrybear.util.Context;

public class HttpServiceHttp extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map param = new HashMap();
//		param.put("regdate", request.getParameter("regdate"));
		String flag = request.getParameter("flag");
		Context context = new Context();
		String path = request.getSession().getServletContext().getRealPath("/");
		context.setContext("APPpath", path);
		try {
			if(!"reg".equals(flag.trim())){
				param.put("account", request.getParameter("account"));
				param.put("name", request.getParameter("name"));
				param.put("idcard", request.getParameter("idcard"));
				context.setContext("param", param);
				DoFlows.doFlows(context, new File(path+"/config/reginfo.xml"));
				request.setAttribute("account", param.get("account"));
				request.getRequestDispatcher("/WEB-INF/reg.jsp").forward(request, response);
			}else{
				param.put("passwd", request.getParameter("pwd"));
				context.setContext("param", param);
				DoFlows.doFlows(context, new File(path+"/config/regflow.xml"));
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
