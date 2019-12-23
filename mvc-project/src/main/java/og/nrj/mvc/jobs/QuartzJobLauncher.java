package og.nrj.mvc.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJobLauncher  extends QuartzJobBean{

	public String jobName;
	public JobLauncher jobLauncher;
	public JobLocator jobLocator;

	
	
	public String getJobName() {
		return jobName;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}



	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}



	public JobLocator getJobLocator() {
		return jobLocator;
	}



	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}



	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobParameters jobParams = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();
		
		
		try {
			Job job = (Job) jobLocator.getJob(jobName);
			JobExecution jobExecution = jobLauncher.run(job, jobParams);
		
			System.out.println("Status : " + jobExecution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchJobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
