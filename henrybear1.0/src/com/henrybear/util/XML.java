/**
 * 
 */
package com.henrybear.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.henrybear.flow.XMLNode;

/**
 * @author Administrator
 *xml ÎÄ¼þ½âÎö
 */
public class XML {

	private File xml;
	private List<XMLNode> xNodeList;
	
	public XML(File xml) throws Exception{
		if(!xml.getName().endsWith(".xml")){
			throw new Exception("File type is not xml");
		}
		this.xml = xml;
		xNodeList = new ArrayList<XMLNode>();
	}
	
	public List<XMLNode> xmlAnalyse() throws DocumentException{
		SAXReader sax = new SAXReader();
		Document doc = sax.read(xml);
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		for(Element e : list){
			XMLNode xNode = new XMLNode();
			Map input = new HashMap();
			xNode.setId(e.attributeValue("id"));
			xNode.setClazz(e.attributeValue("class"));
			List<Element> inputlist = e.element("input").elements();
			for(Element e1 : inputlist){
				input.put(e1.attribute("name").getText(), e1.attribute("value").getText());
			}
			xNode.setInput(input);
			xNodeList.add(xNode);
		}
		return xNodeList;
	}
}
