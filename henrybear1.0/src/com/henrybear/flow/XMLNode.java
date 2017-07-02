/**
 * 
 */
package com.henrybear.flow;

import java.util.Map;
/**
 * @author Administrator
 *
 */
public class XMLNode {
	private String id;
	private String clazz;
	private Map<String,String> input;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Map<String, String> getInput() {
		return input;
	}
	public void setInput(Map<String, String> input) {
		this.input = input;
	}
	@Override
	public String toString() {
		return "XMLNode [id=" + id + ", clazz=" + clazz + ", input=" + input + "]";
	}
	
	
}
