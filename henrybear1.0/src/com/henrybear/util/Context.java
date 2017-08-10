/**
 * 
 */
package com.henrybear.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *иообнд
 */
public class Context<k,v> {
	
	private Map<k,v> map ;
	public Context(){
		this.map = new HashMap<k,v>();
	}
	
	public synchronized void setContext(k key,v value){
		this.map.put(key, value);
	}
	
	public synchronized v getContext(k key){
		return map.get(key);
	}
}