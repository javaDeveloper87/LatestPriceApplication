package com.markit.trade.writer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.markit.trade.model.InstrumentPriceDetails;
import com.markit.trade.model.InstrumentPricesStore;
import com.markit.trade.model.Trade;

/**
 * InstrumentPriceAggregator class containing the reference of 
 * InstrumentPricesStore map to store the latest price of
 * and Id on basis of date.
 * 
 * @author Karan Verma
 *
 */
public class InstrumentPriceAggregator implements ItemWriter<Trade> {

	@Autowired
	private InstrumentPricesStore instrumentPricesStore;

	private static final Logger log = LoggerFactory.getLogger(InstrumentPriceAggregator.class);

	/**
	 * Method to write the latest price details of an instrument
	 * on basis of asOf date field value.
	 */
	@Override
	public void write(List<? extends Trade> trades) throws Exception {
		trades.forEach(instrument -> {
			if (instrumentPricesStore.containsKey(instrument.getInstrumentId())) {
				Date instrumentAsOfPriceDate = instrument.getInstrumentAsOfPriceDate();
				Double instrumentPrice = instrument.getInstrumentPrice();
				InstrumentPriceDetails priceDetails = instrumentPricesStore.get(instrument.getInstrumentId());
				if (instrumentAsOfPriceDate.after(priceDetails.getInstrumentAsOfPriceDate())) {
					priceDetails.setInstrumentPrice(instrumentPrice);
				}			
			} else {
				instrumentPricesStore.put(instrument.getInstrumentId(),
						new InstrumentPriceDetails(instrument.getInstrumentId(),instrument.getInstrumentAsOfPriceDate(),instrument.getInstrumentPrice()));
			}
		});
	}

}
