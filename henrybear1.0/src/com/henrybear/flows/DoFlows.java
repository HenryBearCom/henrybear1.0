/**
 * 
 */
package com.henrybear.flows;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.henrybear.flow.XMLNode;
import com.henrybear.util.Context;
import com.henrybear.util.XML;

/**
 * @author Administrator
 *
 */
public class DoFlows {

	public static void doFlows(Context context,File config) throws Exception{
		Logger log = Logger.getLogger("step");
		log.info("Æô¶¯Á÷³Ì"+config);
		XML xml = new XML(config);
		List<XMLNode> nodes	= xml.xmlAnalyse();
		for(XMLNode node : nodes){
			DoNode.doNode(node, context);
		}
	}
	
}
