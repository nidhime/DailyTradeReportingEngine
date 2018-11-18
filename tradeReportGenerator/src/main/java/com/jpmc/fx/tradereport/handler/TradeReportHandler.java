package com.jpmc.fx.tradereport.handler;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jpmc.fx.tradereport.calender.BaseCalendar;
import com.jpmc.fx.tradereport.model.TradeInstruction;
import com.jpmc.fx.tradereport.util.DateUtil;

/**
 * Abstract class for generating report and calculating Settlement day.
 *
 */
public class TradeReportHandler {
	
	private static Logger logger = Logger.getLogger("DailyReportGenerator");

	private static Map<String, String> CALENDARMAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
		    put("AED", "com.jpmc.fx.tradereport.calender.SunToThursCalendar");
		    put("SAR", "com.jpmc.fx.tradereport.calender.SunToThursCalendar");
		}}; 
			
	private final String DEFAULT_CALENDAR = "com.jpmc.fx.tradereport.calender.DefaultCalendar";
	
	/**
	 * Method to prepare a map with trade date and settled amount.
	 * @param trade
	 * @param finalMap
	 */
	public void generateDialyReport(TradeInstruction trade, Map<String,Double> settledAmountMap) {
		Double amount = trade.getRate() * trade.getNoOfUnits() * trade.getPricePerUnit();
		
		try {
			LocalDate localDate = DateUtil.returnDateFromString(trade.getSettlementDate());
			LocalDate businessDate = null;
			
			try {
				if(CALENDARMAP.containsKey(trade.getCurrency()) ) {
					businessDate = ((BaseCalendar)Class.forName(CALENDARMAP.get(trade.getCurrency())).getDeclaredConstructor().newInstance()).getBusinessDay(localDate);
				} else {
					businessDate = ((BaseCalendar)Class.forName(DEFAULT_CALENDAR).getDeclaredConstructor().newInstance()).getBusinessDay(localDate);
				}
				//logger.info("Business Day for Currency: " + trade.getCurrency() + " on settlementDate: "+ localDate + " is: "+ businessDate);
				
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Exception while parsing settlement date" +e);
				throw new IllegalArgumentException(e);
			} 
			
			String businessDateStr = DateUtil.returnDateToString(businessDate);
			if(settledAmountMap.containsKey(businessDateStr)){
				settledAmountMap.put(businessDateStr, Double.sum(settledAmountMap.get(businessDateStr), amount));
			}
			else{
				settledAmountMap.put(businessDateStr, amount);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	
}
