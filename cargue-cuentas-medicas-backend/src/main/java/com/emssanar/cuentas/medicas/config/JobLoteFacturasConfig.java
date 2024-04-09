package com.emssanar.cuentas.medicas.config;

import javax.validation.Validator;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.emssanar.cuentas.medicas.entity.Factura;
import com.emssanar.cuentas.medicas.step.FacturaItemProcessor;
import com.emssanar.cuentas.medicas.step.FacturaItemWriter;

@Configuration
@EnableBatchProcessing
public class JobLoteFacturasConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
    private Validator validator;

	@Bean
	public JobLauncher jobLoteFacturasLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Bean
	public Job procesarLoteFacturasJob(JobLoteFacturasListener listener, Step stepOne) {
		return jobBuilderFactory.get("procesarLoteFacturasJob").listener(listener).flow(stepOne).end().build();
	}

	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("stepOne").<Factura, Factura>chunk(5) // procesa en bloques de 5
				.reader(reader(null)).
				processor(processor(null)).
				writer(writer()).
				taskExecutor(taskExecutor()).build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(1);
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setQueueCapacity(5);
		return taskExecutor;
	}

	@Bean
	@StepScope
	public JsonItemReader<Factura> reader(@Value("#{jobParameters[fullPathFileName]}") String fileJson) {
		return new JsonItemReaderBuilder<Factura>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(Factura.class)).resource(new PathResource(fileJson))
				.name("facturaJsonItemReader").build();
	}

	@Bean
	@StepScope
	public FacturaItemProcessor processor(@Value("#{jobParameters[identificadorCargue]}") Long cargueId) {
		return new FacturaItemProcessor(validator,cargueId);
	}

	@Bean
	public FacturaItemWriter writer() {
		return new FacturaItemWriter();
	}

}
