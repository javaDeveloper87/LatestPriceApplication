package com.markit.trade.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.markit.trade.model.InstrumentPricesStore;

/**
 * Listener class which provide the status of job
 * after execution.
 * @author Karan Verma
 *
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {


	@Autowired
	private InstrumentPricesStore instrumentPricesStore;

	/**
	 * Overridden method to check the status of job completion.
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.err.println("Batch has been successfully processed....");
			instrumentPricesStore.updateBatchPrice();
		}
	}
}
