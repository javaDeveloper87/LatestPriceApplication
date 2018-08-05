package com.markit.trade.model;

import java.util.Date;

/**
 * InstrumentPriceDetails model containing id, price and date fields.
 * @author Karan Verma
 *
 */
public class InstrumentPriceDetails {

	public InstrumentPriceDetails() {
		
	}

	private String instrumentId;
	private Date instrumentAsOfPriceDate;
	private Double instrumentPrice;

	public InstrumentPriceDetails(String id, Date asOfDate, Double price) {
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
	 * @param instrumentId
	 *            the instrumentId to set
	 */
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	/**
	 * @return the instrumentAsOfPriceDate
	 */
	public Date getInstrumentAsOfPriceDate() {
		return instrumentAsOfPriceDate;
	}

	/**
	 * @param instrumentAsOfPriceDate
	 *            the instrumentAsOfPriceDate to set
	 */
	public void setInstrumentAsOfPriceDate(Date instrumentAsOfPriceDate) {
		this.instrumentAsOfPriceDate = instrumentAsOfPriceDate;
	}

	/**
	 * @return the instrumentPrice
	 */
	public Double getInstrumentPrice() {
		return instrumentPrice;
	}

	/**
	 * @param instrumentPrice
	 *            the instrumentPrice to set
	 */
	public void setInstrumentPrice(Double instrumentPrice) {
		this.instrumentPrice = instrumentPrice;
	}

	@Override
	public String toString() {
		return "InstrumentPriceDetails [instrumentId=" + instrumentId + ", instrumentAsOfPriceDate="
				+ instrumentAsOfPriceDate + ", instrumentPrice=" + instrumentPrice + "]";
	}

}
