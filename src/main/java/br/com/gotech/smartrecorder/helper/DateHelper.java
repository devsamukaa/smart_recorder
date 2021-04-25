package br.com.gotech.smartrecorder.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {
	
	
	public static String toDateTimeString(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Brazil"));
		return dateFormat.format(date);
		
	}
	
	public static String toDateString(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Brazil"));
		return dateFormat.format(date);
		
	}
	
	public static String toDateStringCustom(Date date, String stringDateFormat) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Brazil"));
		
		return dateFormat.format(date);
		
	}
	
	public static Date parseDateCustom(String dateString, String stringDateFormat) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Brazil"));
		Date date = null;
		
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
		
	}
	
	public static Date parseDate(String dateString) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Brazil"));

		Date date = null;
		
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
		
	}
	
}
