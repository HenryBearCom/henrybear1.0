/**
 * 
 */
package com.henrybear.bean;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class RegInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7038325586026258664L;
	
	private String account;
	private String name;
	private String sex;
	private String idcard;
	private String tel;
	private String email;
	private String regdate;
	private String bak1;
	private String bak2;
	private String bak3;
	private String bak4;
	private String bak;
	
	private String password;
	
	private String title;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getBak3() {
		return bak3;
	}
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}
	public String getBak4() {
		return bak4;
	}
	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	@Override
	public String toString() {
		return "RegInfo [account=" + account + ", name=" + name + ", sex="
				+ sex + ", idcard=" + idcard + ", tel=" + tel + ", email="
				+ email + ", regdate=" + regdate + ", bak1=" + bak1 + ", bak2="
				+ bak2 + ", bak3=" + bak3 + ", bak4=" + bak4 + ", bak=" + bak
				+ "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
