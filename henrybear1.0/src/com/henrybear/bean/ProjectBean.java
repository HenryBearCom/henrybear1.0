package com.henrybear.bean;

import com.alibaba.fastjson.JSON;

public class ProjectBean {
	private String serial;
	private String serialname;
	private String description;
	private String fuzeren;
	private String fuzerentel;
	private String starttime;
	private String deadtime;
	private String bak2;
	private String bak;
	private String bak1;
	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getSerialname() {
		return serialname;
	}
	public void setSerialname(String serialname) {
		this.serialname = serialname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFuzeren() {
		return fuzeren;
	}
	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}
	public String getFuzerentel() {
		return fuzerentel;
	}
	public void setFuzerentel(String fuzerentel) {
		this.fuzerentel = fuzerentel;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getDeadtime() {
		return deadtime;
	}
	public void setDeadtime(String deadtime) {
		this.deadtime = deadtime;
	}
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	
	public String toJson() {
		return JSON.toJSONString(this);
	}
}
