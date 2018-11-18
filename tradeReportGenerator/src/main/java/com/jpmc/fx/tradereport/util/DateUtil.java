package com.jpmc.fx.tradereport.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Author - Nidhi Mehra
 * Utility class for date conversions
 *
 */
public class DateUtil {

	/**
	 * @param inputString
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate returnDateFromString(String inputString) throws ParseException{
		
		return LocalDate.parse(inputString);
	}
	
	/**
	 * 
	 * @param inputString
	 * @return
	 * @throws ParseException
	 */
	public static String returnDateToString(LocalDate inputString) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        return inputString.format(formatter);

	}
}
