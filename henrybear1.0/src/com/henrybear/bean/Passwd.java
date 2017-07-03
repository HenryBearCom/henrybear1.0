/**
 * 
 */
package com.henrybear.bean;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Passwd implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7411365723248878194L;
	private String account;
	private String passwd;
	private String bak1;
	private String bak2;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	@Override
	public String toString() {
		return "Passwd [account=" + account + ", passwd=" + passwd + ", bak1="
				+ bak1 + ", bak2=" + bak2 + "]";
	}
}
