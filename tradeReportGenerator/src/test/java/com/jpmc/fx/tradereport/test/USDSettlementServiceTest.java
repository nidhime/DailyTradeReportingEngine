package com.jpmc.fx.tradereport.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.fx.tradereport.model.TradeInstruction;
import com.jpmc.fx.tradereport.service.RankingsService;
import com.jpmc.fx.tradereport.service.USDSettlementService;
import com.jpmc.fx.tradereport.util.FileReaderUtil;

public class USDSettlementServiceTest {

	List<TradeInstruction> instructions;
	
	@Before
	public void setUp(){
		instructions = FileReaderUtil.processInputFile();
	}
	
	@Test
	public void givenBuyGenerateOutGoingReport() {
		//when
		Map<String, Double> data = USDSettlementService.generateAmountSettledReport(instructions, "B");
		assertEquals("23025.0", data.get("2017-01-02").toString());
	}

	@Test
	public void givenSellGenerateIncomingReport() {
		//when
		Map<String, Double> data = USDSettlementService.generateAmountSettledReport(instructions, "S");
		assertEquals("3000.0", data.get("2017-01-02").toString());
	}
	
	@Test
	public void givenAEDSettleOnSunday() {
		//when
		assertEquals(instructions.get(2).getCurrency().toString(), "AED");
		assertEquals(instructions.get(2).getSettlementDate().toString(), "2017-01-07");
		Map<String, Double> data = USDSettlementService.generateAmountSettledReport(instructions, "S");
		assertEquals("14899.5", data.get("2017-01-08").toString());
	}
	
	@Test
	public void givenSGPSettleOnMonday() {
		//when
		assertEquals(instructions.get(0).getCurrency().toString(), "SGP");
		assertEquals(instructions.get(0).getSettlementDate().toString(), "2017-01-01");
		Map<String, Double> data = USDSettlementService.generateAmountSettledReport(instructions, "B");
		assertEquals("23025.0", data.get("2017-01-02").toString());
	}
	
	@Test
	public void givenAllInstructionsGenerateRankReport() {
		//when
		Map<String, Map<String, Double>> data = RankingsService.prepareRankings(instructions);
		assertEquals("{hsbc=20025.0, hdfc=14899.5, icici=3000.0}", data.get("Incoming Rankings:").toString());
		assertEquals("20025.0", data.get("Incoming Rankings:").get("hsbc").toString());
		assertEquals("{lloyds=22729.5, citi=14899.5, icici=12949.5}", data.get("Outgoing Rankings:").toString());
		assertEquals("22729.5", data.get("Outgoing Rankings:").get("lloyds").toString());
	}
	
	@After
	public void end(){
		instructions = null;
	}
}
