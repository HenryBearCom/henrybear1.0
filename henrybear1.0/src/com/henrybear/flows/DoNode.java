package com.henrybear.flows;

import org.apache.log4j.Logger;

import com.henrybear.flow.XMLNode;
import com.henrybear.util.Context;

public class DoNode {

	public static void doNode(XMLNode node,Context context) throws Exception{
		Logger log = Logger.getLogger("step");
		String clazz = node.getClazz();
		Class obj = Class.forName(clazz);
		Step step = (Step) obj.newInstance();
		context.setContext(node.getId(), node.getInput());
		context.setContext("stepid", node.getId());
		log.info("准备执行"+node.getId()+"...");
		step.executor(context);
		log.info(node.getId()+"执行完成");
	}
	
}
