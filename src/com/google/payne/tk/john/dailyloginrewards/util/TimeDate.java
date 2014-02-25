package com.google.payne.tk.john.dailyloginrewards.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeDate {
	
	public static int getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		
		/*if (Integer.parseInt(dateFormat.format(date)) != Settings.Today) {
			Settings.LoadConfig();
		}*/
		
		return Integer.parseInt(dateFormat.format(date));
	}
	
	public static int getYesterdaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		return Integer.parseInt(dateFormat.format(cal.getTime()));
		
	}
}
