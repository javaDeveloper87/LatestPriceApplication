package com.markit.trade.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * InstrumentPricesStore class containing a map to store the
 * id with latest price details.
 * @author Karan Verma
 *
 */
public class InstrumentPricesStore {
	
	private Map<String, InstrumentPriceDetails> chunkStockPrices = new HashMap<String, InstrumentPriceDetails>();
	private Map<String, InstrumentPriceDetails> batchStockPrices;

	public boolean containsKey(Object key) {
		return chunkStockPrices.containsKey(key);
	}

	public InstrumentPriceDetails put(String key, InstrumentPriceDetails value) {
		return chunkStockPrices.put(key, value);
	}

	public Collection<InstrumentPriceDetails> values() {
		return chunkStockPrices.values();
	}

	public InstrumentPriceDetails get(Object key) {
		return chunkStockPrices.get(key);
	}
	
	public void updateBatchPrice() {
		batchStockPrices = new HashMap<String, InstrumentPriceDetails>();
		batchStockPrices.putAll(chunkStockPrices);
	}

	public InstrumentPriceDetails getUpdatedPrice(Object key) {
		if(null!=batchStockPrices && !batchStockPrices.isEmpty()) {
		return batchStockPrices.get(key);
		}
		return new InstrumentPriceDetails();
	}
}
