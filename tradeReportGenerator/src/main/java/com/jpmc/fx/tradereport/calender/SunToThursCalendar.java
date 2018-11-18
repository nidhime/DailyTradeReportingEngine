package com.jpmc.fx.tradereport.calender;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SunToThursCalendar extends BaseCalendar {

	/**
	 * Method to check holidays based on calendar
	 */
	@Override
	public boolean isHoliday(LocalDate currentDay) {
		return (currentDay.getDayOfWeek().name().equals(DayOfWeek.FRIDAY.name())  
				|| currentDay.getDayOfWeek().name().equals(DayOfWeek.SATURDAY.name()));
	}
}
