package com.markit.trade.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 /**
  * Controller class to launch the job and start processing.
  * Url is exposed in order to start it.
  * @author Karan Verma
  *
  */
@RestController
public class JobLauncherController {
 
	@Autowired
	JobLauncher jobLauncher;
 
	@Autowired
	Job job;
 
	/**
	 * Method to launch the job batch processing by hitting mentioned
	 * URI.
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/jobLauncher")
	public String handle() throws Exception {
		TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		final JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		taskExecutor.execute(new Runnable() {
		    public void run() {
		        try {
		        	jobLauncher.run(job, jobParameters);
		        } catch (Exception e){
		            e.printStackTrace();
		            System.err.println("Error occured while launchig job"+e);
		        }
		    }
		});
			return "jobLauncher Started successfully";
	}
}
