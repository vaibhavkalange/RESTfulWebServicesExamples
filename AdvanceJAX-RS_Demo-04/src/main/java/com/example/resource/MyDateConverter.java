package com.example.resource;

import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;

public class MyDateConverter implements ParamConverter<MyDate> {

	@Override
	public MyDate fromString(String value) {
		Calendar cal = Calendar.getInstance();
		
		if("tomorrow".equalsIgnoreCase(value)){
			cal.add(Calendar.DATE, 1);
		}
		else if("yesterday".equalsIgnoreCase(value)){
			cal.add(Calendar.DATE, -1);
		}
		
		MyDate date = new MyDate();
		date.setDay(cal.get(Calendar.DATE));
		date.setMonth(cal.get(Calendar.MONTH));
		date.setYear(cal.get(Calendar.YEAR));
		
		return date;
	}

	@Override
	public String toString(MyDate date) {
		if(date == null){
			return null;
		}
		return date.toString();
		
	}

}
