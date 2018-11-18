package com.jpmc.fx.tradereport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmc.fx.tradereport.handler.TradeReportHandler;
import com.jpmc.fx.tradereport.model.TradeInstruction;

public class USDSettlementService {
	/**
	 * 
	 * @param instructions
	 * @param direction
	 * @return
	 */
	public static Map<String,Double> generateAmountSettledReport(List<TradeInstruction> instructions, String direction) {
		
		Map<String,Double> settledAmountMap = new HashMap<>();
		TradeReportHandler tradeReportHandler = new TradeReportHandler();
		
		instructions = filterByDirection(instructions, direction);
		instructions.forEach(trade -> {
			tradeReportHandler.generateDialyReport(trade, settledAmountMap);
		});
		
		return settledAmountMap;
	}
	
	/**
	 * Return a new list based on the Buy/Sell indicator 
	 * @param instructions
	 * @param direction
	 * @return
	 */
	public static List<TradeInstruction> filterByDirection(List<TradeInstruction> instructions, String direction) {
		return instructions.stream().filter(trade -> (trade != null && trade.getDirection().equals(direction))).collect(Collectors.toList());
		
	}
	
}




