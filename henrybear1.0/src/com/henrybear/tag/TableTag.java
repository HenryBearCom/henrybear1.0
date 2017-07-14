package com.henrybear.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TableTag extends TagSupport {

	private static final long serialVersionUID = 9220790290851664297L;

	private String name;

	public int doEndTag(){
		JspWriter out = this.pageContext.getOut();
		try {
			out.print("<div id='"+name+"' > hello "+name+"</div>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
