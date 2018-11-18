package com.jpmc.fx.tradereport.model;

public enum Instruction {

	BUY("B", "Incoming Rankings:"),
	SELL("S", "Outgoing Rankings:");
	
	private String instr;
	
	private String description;
	
	Instruction(String instr, String description) {
		this.instr = instr;
		this.description = description;
	}
	
	public String value() {
		return instr;
	}
	
	public String description() {
		return description;
	}
}
