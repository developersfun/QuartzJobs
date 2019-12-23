package og.nrj.mvc.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;	
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	public MyReader reader() {
		return new MyReader();
	}
	
	public MyWriter writer() {
		return new MyWriter();
	}
	
	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("stepOne")
				.<Object,Object> chunk(10)
				.reader(reader())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
				.incrementer(new RunIdIncrementer())
				.flow(stepOne())
				.end()
				.build();
	
	}
	
	
}
