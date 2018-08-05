package com.markit.trade.reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.markit.trade.model.InstrumentPriceDetails;

/**
 * CustomFieldSetMapper class to map the file columns values with
 * InstrumentPriceDetails object in specific format.
 * @author Karan Verma
 *
 */
public class CustomFieldSetMapper implements FieldSetMapper<InstrumentPriceDetails> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	/**
	 * Overriden mapFieldSet Object to map the fields to a defined object.
	 */
	@Override
	public InstrumentPriceDetails mapFieldSet(FieldSet fieldSet) throws BindException {
		
		InstrumentPriceDetails instrumentPriceDetails = new InstrumentPriceDetails();
		instrumentPriceDetails.setInstrumentId(fieldSet.readString(0));
		instrumentPriceDetails.setInstrumentPrice(fieldSet.readDouble(2));
		String date = fieldSet.readString(1);
		try {
			dateFormat.setLenient(false);
			instrumentPriceDetails.setInstrumentAsOfPriceDate(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return instrumentPriceDetails;
		
	}

}
