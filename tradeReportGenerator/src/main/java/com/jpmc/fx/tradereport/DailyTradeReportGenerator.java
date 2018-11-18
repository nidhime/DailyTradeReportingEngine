package com.jpmc.fx.tradereport;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jpmc.fx.tradereport.model.Instruction;
import com.jpmc.fx.tradereport.model.TradeInstruction;
import com.jpmc.fx.tradereport.service.RankingsService;
import com.jpmc.fx.tradereport.service.USDSettlementService;
import com.jpmc.fx.tradereport.util.FileReaderUtil;

public class DailyTradeReportGenerator {
	
	final static Logger logger = Logger.getLogger("DailyTradeReportingEngine");

	public static void main(String[] args) {
		
		logger.log(Level.INFO,"Debug Info : ");
		
		List<TradeInstruction> instructions = FileReaderUtil.processInputFile();
		System.out.println("USD Settled Incoming(Sell) Report everyday :");
		System.out.println(USDSettlementService.generateAmountSettledReport(instructions, Instruction.SELL.value()));
		System.out.println("USD Settled Outgoing(Buy) Report everyday :"); 
		System.out.println( USDSettlementService.generateAmountSettledReport(instructions, Instruction.BUY.value()));
		System.out.println("Ranking Report");
		System.out.println(RankingsService.prepareRankings(instructions));

	}

}
