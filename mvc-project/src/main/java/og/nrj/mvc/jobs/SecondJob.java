package og.nrj.mvc.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SecondJob implements Job {
	
	int count = 1;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		// TODO Auto-generated method stub
		System.out.println("Running...!!" + (count++));
		
		
	}

}
