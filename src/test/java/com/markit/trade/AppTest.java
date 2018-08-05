package com.markit.trade;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.markit.trade.config.InstrumentPriceBatchConfiguration; 
import com.markit.trade.controller.InstrumentPriceController;
import com.markit.trade.model.InstrumentPricesStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InstrumentPriceBatchConfiguration.class,JobLauncherTestUtils.class,InstrumentPriceController.class})
@PropertySource("spring.batch.job.enabled=true")
public class AppTest {
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	@InjectMocks
    private InstrumentPriceController instrumentPriceController;
	
	@Autowired
	InstrumentPricesStore instrumentPriceStore;
	
    
    @Test
    public void launchJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        
    }
    
    @Test
    public void testGetInstrumentPriceDetail() throws Exception {
    	JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    	String id="JNJ";
        Double price = instrumentPriceController.getInstrumentPriceDetail(id);
        Assert.assertEquals(price,new Double(91.14));
        
    }
    
}
