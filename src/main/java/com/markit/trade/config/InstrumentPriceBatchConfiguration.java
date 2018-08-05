package com.markit.trade.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.markit.trade.listener.JobCompletionNotificationListener;
import com.markit.trade.model.InstrumentPriceDetails;
import com.markit.trade.model.InstrumentPricesStore;
import com.markit.trade.model.Trade;
import com.markit.trade.processor.InstrumentPriceBatchProcessor;
import com.markit.trade.reader.InstrumentPriceBatchReader;
import com.markit.trade.writer.InstrumentPriceAggregator;

/**
 * Configuration class to configure the jobs and steps for processing
 * and would create beans on server start-up.
 * @author Karan Verma
 *
 */
@Configuration
@EnableBatchProcessing
@PropertySource("classpath:batch.properties")
public class InstrumentPriceBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public InstrumentPricesStore instrumentPricesStore() {
		return new InstrumentPricesStore();
	}

	@Bean
	public InstrumentPriceBatchReader instrumentPriceBatchReader() {
		return new InstrumentPriceBatchReader();
	}

	@Bean
	public InstrumentPriceBatchProcessor instrumentPriceBatchProcessor() {
		return new InstrumentPriceBatchProcessor();
	}

	@Bean
	public InstrumentPriceAggregator instrumentPriceAggregator() {
		return new InstrumentPriceAggregator();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// Configure job
	@Bean
	public Job instrumentPricesETLJob() {
		return jobBuilderFactory.get("LastestPriceService").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(etlStep()).end().build();
	}

	//// Configure job steps in order
	@Bean
	public Step etlStep() {
		return stepBuilderFactory.get("LastestPriceServiceStep").<InstrumentPriceDetails, Trade> chunk(10)
				.reader(instrumentPriceBatchReader()).processor(instrumentPriceBatchProcessor())
				.writer(instrumentPriceAggregator())
				.build();
	}
	
}
