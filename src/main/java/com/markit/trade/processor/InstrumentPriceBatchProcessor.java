package com.markit.trade.processor;

import org.springframework.batch.item.ItemProcessor;

import com.markit.trade.model.InstrumentPriceDetails;
import com.markit.trade.model.Trade;

/**
 * InstrumentPriceBatchProcessor class to process the instrument details.
 * @author Karan Verma
 *
 */
public class InstrumentPriceBatchProcessor implements ItemProcessor<InstrumentPriceDetails, Trade> {

	/**
	 * Overridden process method to process and map the input object
	 * to user defined object for further processing.
	 */
	@Override
	public Trade process(InstrumentPriceDetails instrumentPriceDetails) throws Exception {
		final Trade trade = new Trade(instrumentPriceDetails.getInstrumentId(), instrumentPriceDetails.getInstrumentAsOfPriceDate(),instrumentPriceDetails.getInstrumentPrice());
		return trade;
	}

}
