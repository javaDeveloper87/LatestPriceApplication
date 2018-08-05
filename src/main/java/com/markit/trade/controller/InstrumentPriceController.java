package com.markit.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.markit.trade.model.InstrumentPriceDetails;
import com.markit.trade.model.InstrumentPricesStore;

/**
 * InstrumentPriceController class to get the price for an instrument id.
 * @author Karan Verma
 *
 */
@RestController
public class InstrumentPriceController {
	
	@Autowired
	InstrumentPricesStore instrumentPriceStore;
	
	/**
	 * Method exposed to fetch the instrument price for an ID.
	 * @param id of String type
	 * @return price of Double type
	 */
	@RequestMapping(path="/instrumentPrice/{id}",produces="application/json",method=RequestMethod.GET)
	public Double getInstrumentPriceDetail(@PathVariable String id) {
		InstrumentPriceDetails instrumentPriceDetails = instrumentPriceStore.getUpdatedPrice(id);
		return instrumentPriceDetails.getInstrumentPrice();
	}
	
}
