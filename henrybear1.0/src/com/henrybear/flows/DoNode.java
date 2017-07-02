package com.henrybear.flows;

import com.henrybear.flow.XMLNode;
import com.henrybear.util.Context;

public class DoNode {

	public static void doNode(XMLNode node,Context context) throws Exception{
		String clazz = node.getClazz();
		Class obj = Class.forName(clazz);
		Step step = (Step) obj.newInstance();
		context.setContext(node.getId(), node.getInput());
		System.out.println("准备执行"+node.getId()+"...");
		step.executor(context);
		System.out.println(node.getId()+"执行完成");
	}
	
}
