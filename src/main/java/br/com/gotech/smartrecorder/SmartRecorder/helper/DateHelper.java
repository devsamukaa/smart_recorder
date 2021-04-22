package br.com.gotech.cyrela.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	
	
	public static String toDateTimeString(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return dateFormat.format(date);
		
	}
	
	public static String toDateString(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormat.format(date);
		
	}
	
	public static String toDateStringCustom(Date date, String stringDateFormat) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
		
		return dateFormat.format(date);
		
	}
	
	public static Date parseDateCustom(String dateString, String stringDateFormat) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
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
