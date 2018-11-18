package com.jpmc.fx.tradereport.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.jpmc.fx.tradereport.model.TradeInstruction;

public class TradeValidator {
	
	/**
	 * Trade Validator to validate file fields
	 * @param instr
	 * @return
	 */
	public static Boolean validateRecord(TradeInstruction instr) {
		
		Boolean isValid = true;
		isValid = ((instr.getCurrency() == null)?false:true);
		isValid = ((instr.getEntity() == null)?false:true);
		isValid = ((instr.getDirection() == null)?false:true);
		isValid = ((instr.getNoOfUnits() == 0)?false:true);
		isValid = ((instr.getRate() == null)?false:true);
		isValid = ((instr.getPricePerUnit() == null)?false:true);
		
		try {
			LocalDate.parse(instr.getInstructionDate());
			LocalDate.parse(instr.getSettlementDate());
		} catch (DateTimeParseException dpe) {
			isValid = false;
		}
		
		return isValid;
	}
}
