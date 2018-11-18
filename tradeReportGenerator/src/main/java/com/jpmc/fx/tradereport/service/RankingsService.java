package com.jpmc.fx.tradereport.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmc.fx.tradereport.model.Instruction;
import com.jpmc.fx.tradereport.model.TradeInstruction;

public class RankingsService {
	
	/**
	 * Method to prepare Settled Amount Map for each entity based for Incoming/Outgoing.
	 * @param instructions
	 * @return
	 */
	public static Map<String, Map<String,Double>> prepareRankings(List<TradeInstruction> instructions) {
		Map<String, Map<String,Double>> rankingsMap = new HashMap<String, Map<String, Double>>();
		
		List<TradeInstruction> incomingList = filterByDirection(instructions, Instruction.SELL.value());
		rankingsMap.put(Instruction.SELL.description(), getEntityRankingsMap(incomingList));
		
		List<TradeInstruction> outgoingList = filterByDirection(instructions, Instruction.BUY.value());
		rankingsMap.put(Instruction.BUY.description(),  getEntityRankingsMap(outgoingList));
		
		return rankingsMap;
	    
	}

	/**
	 * Return a Map with Entity and corresponding Settled USD Amount.
	 * @param tradeInstructions
	 * @return
	 */
	private static Map<String, Double> getEntityRankingsMap(List<TradeInstruction> tradeInstructions) {
		Map<String, Double> rankingsMap = new HashMap<String, Double>();
		Map<String, Double> sortedIncomingMap = new LinkedHashMap<>();
		

		tradeInstructions.forEach(trade -> {
			if(rankingsMap.containsKey(trade.getEntity())) {
				rankingsMap.put(trade.getEntity(), Double.sum(rankingsMap.get(trade.getEntity()), trade.getPricePerUnit()*trade.getNoOfUnits()*trade.getRate()));
			} else {
				rankingsMap.put(trade.getEntity(), trade.getPricePerUnit()*trade.getNoOfUnits()*trade.getRate());
			}
			
		});
		
		rankingsMap.entrySet().stream()
        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
        .forEachOrdered(x -> sortedIncomingMap.put(x.getKey(), x.getValue()));

		return sortedIncomingMap;
	}
	
	/**
	 * Return a new list based on the Buy/Sell indicator 
	 * @param instructions
	 * @param direction
	 * @return
	 */
	public static List<TradeInstruction> filterByDirection(List<TradeInstruction> tradesList, String direction) {
		return tradesList.stream().filter(trade -> (trade != null && trade.getDirection().equals(direction))).collect(Collectors.toList());
		
	}
	
}
