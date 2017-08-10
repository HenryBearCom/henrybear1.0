package com.henrybear.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getCurrDateByPattern(String pattern){
		Date currDate = new Date();
		SimpleDateFormat formate = new SimpleDateFormat(pattern);
		return formate.format(currDate);
	}
	
	public static String chgDatePattern(String date,String oldPatt,String toPatt) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(oldPatt);
		Date toDate = format.parse(date);
		format = new SimpleDateFormat(toPatt);
		return format.format(toDate);
	}
	
	public static Date conver2Date(String date,String patt) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(patt);
		return format.parse(date);
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String time = getCurrDateByPattern("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(time);
		System.out.println(chgDatePattern(time, "yyyy-MM-dd HH:mm:ss.SSS", "yyyy/MM/dd"));
		System.out.println(conver2Date(chgDatePattern(time, "yyyy-MM-dd HH:mm:ss.SSS", "yyyy/MM/dd"), "yyyy/MM/dd"));
	}

}
