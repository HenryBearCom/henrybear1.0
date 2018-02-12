package com.henrybear.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class String2DateConverter implements Converter<String, Date> {

	// ��������ģ�壺��yyyy-MM-dd
		private String datePattern;
		
		public void setDatePattern(String datePattern) {
			this.datePattern = datePattern;
		}

		// Converter<S,T>�ӿڵ�����ת������
		@Override
		public Date convert(String date) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(this.datePattern);
				// �������ַ���ת����Date���ͷ���
				return dateFormat.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("����ת��ʧ��!");
				return null;
			}
			
		}

}
