/**
 * 
 */
package com.henrybear.flows;

import java.io.File;
import java.util.List;

import com.henrybear.flow.XMLNode;
import com.henrybear.util.Context;
import com.henrybear.util.XML;

/**
 * @author Administrator
 *
 */
public class DoFlows {

	public static void doFlows(Context context,File config) throws Exception{
		XML xml = new XML(config);
		List<XMLNode> nodes	= xml.xmlAnalyse();
		for(XMLNode node : nodes){
			context.setContext("stepnode", node.getId());
			DoNode.doNode(node, context);
		}
	}
	
}
