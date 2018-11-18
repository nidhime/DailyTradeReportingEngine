package com.jpmc.fx.tradereport.model;


/**
 * Bean for TradeInstruction
 * 
 */
public class TradeInstruction {
	
	private String entity;
	
	private String direction;
	
	private Double rate;
	
	private String currency;
	
	private String instructionDate;
	
	private String settlementDate;
	
	private int noOfUnits;
	
	private Double pricePerUnit;

	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getNoOfUnits() {
		return noOfUnits;
	}
	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	@Override
	public String toString(){
		return "\n Entity: " +entity +" Buy/Sell: "+ direction +" AgreedFx: " +rate
				+" Currency: " + currency +"InstructionDate: " + instructionDate
				+" SettlementDate: " +settlementDate +" Units: "+ noOfUnits + " Price Per Unit:" +pricePerUnit +"\n";
	}

}
