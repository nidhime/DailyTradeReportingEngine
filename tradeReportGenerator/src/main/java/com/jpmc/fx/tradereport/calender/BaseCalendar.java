package com.jpmc.fx.tradereport.calender;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class BaseCalendar {

	/**
	 * Method to get 
	 * @param currentDay
	 * @return
	 */
	public LocalDate getBusinessDay(LocalDate currentDay) {
		while(isHoliday(currentDay)) {
			currentDay = currentDay.plus(1, ChronoUnit.DAYS);
		}
		return currentDay;
	}
	
	public abstract boolean isHoliday(LocalDate currentDay); 
}
