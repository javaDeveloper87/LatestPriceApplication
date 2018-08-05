package com.markit.trade.reader;

import java.io.IOException;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.markit.trade.model.InstrumentPriceDetails;

/**
 * InstrumentPriceBatchReader class to load the file into the system
 * and invoke the CustomFieldSetmapper to set the values from file.
 * @author Karan Verma
 *
 */
public class InstrumentPriceBatchReader extends MultiResourceItemReader<InstrumentPriceDetails> {

	public InstrumentPriceBatchReader() {
		 //MultiResourceItemReader<InstrumentPriceDetails> resourceItemReader = new MultiResourceItemReader<InstrumentPriceDetails>();
		    Resource[] resources = null;
		    ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();   
		    try {
		        resources = patternResolver.getResources("*.csv");
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.err.println("Error while loading csv" +e);
		    }
		    this.setResources(resources);
		    FlatFileItemReader<InstrumentPriceDetails> reader = new FlatFileItemReader<InstrumentPriceDetails>();
		    reader.setLinesToSkip(1);
		    reader.setLineMapper(new DefaultLineMapper<InstrumentPriceDetails>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "instrumentId", "instrumentAsOfPriceDate", "instrumentPrice"});
					}
				});
				setFieldSetMapper(new CustomFieldSetMapper());
				
			}
		});
		    this.setDelegate(reader);	
	}

}
