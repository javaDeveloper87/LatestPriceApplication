package com.markit.trade.model;

import java.util.Date;

/**
 * Trade class to map the input values to user defined object.
 * @author Karan Verma
 *
 */
public class Trade {

	private String instrumentId;
	private Date instrumentAsOfPriceDate;
	private Double instrumentPrice;

	public Trade(String id, Date asOfDate, Double price) {
		this.instrumentId = id;
		this.instrumentAsOfPriceDate = asOfDate;
		this.instrumentPrice = price;
	}
	
	
	
	/**
	 * @return the instrumentId
	 */
	public String getInstrumentId() {
		return instrumentId;
	}



	/**
	 * @return the instrumentAsOfPriceDate
	 */
	public Date getInstrumentAsOfPriceDate() {
		return instrumentAsOfPriceDate;
	}



	/**
	 * @return the instrumentPrice
	 */
	public Double getInstrumentPrice() {
		return instrumentPrice;
	}



	@Override
	public String toString() {
		return "InstrumentPriceDetails [instrumentId=" + instrumentId + ", instrumentAsOfPriceDate=" + instrumentAsOfPriceDate + 
				", instrumentPrice=" + instrumentPrice + "]";
	}

}
