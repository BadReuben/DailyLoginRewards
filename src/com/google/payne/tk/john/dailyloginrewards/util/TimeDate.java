package com.google.payne.tk.john.dailyloginrewards.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.google.payne.tk.john.dailyloginrewards.configuration.Settings;

public class TimeDate {	
	public static int getTodaysDate() {
		NumberFormat numberformat = new DecimalFormat("00");
		Calendar cal = Calendar.getInstance(getTimeZone());
		
		//Timezone-modified date. First month is valued at zero, so a "+100" was added in the return
		String date = cal.get(Calendar.YEAR) + (numberformat.format(cal.get(Calendar.MONTH))) + numberformat.format(cal.get(Calendar.DATE));
		
		return Integer.parseInt(date) + 100;
	}
	
	public static int getYesterdaysDate() {
		NumberFormat numberformat = new DecimalFormat("00");
		Calendar cal = Calendar.getInstance(getTimeZone());
		cal.add(Calendar.DATE, -1);
		
		String date = cal.get(Calendar.YEAR) + (numberformat.format(cal.get(Calendar.MONTH))) + numberformat.format(cal.get(Calendar.DATE));
		
		return Integer.parseInt(date) + 100;
	}
	
	public static TimeZone getTimeZone() {
		//Attempts to set the timezone specified in the config
		TimeZone tz = TimeZone.getTimeZone(Settings.timezone);
		return tz;
	}
}
