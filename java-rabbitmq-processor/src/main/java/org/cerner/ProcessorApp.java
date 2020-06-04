package org.cerner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Transformer;

import com.google.gson.Gson;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorApp {

	private static Logger logger = LoggerFactory.getLogger(ProcessorApp.class);

	@Autowired
	private Gson gson;

	@Bean
	public Gson getGson() {
		return new Gson();
	}

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object transform(List<?> patients) {

		logger.info("Input: " + patients.toString());

		String json = gson.toJson(patients);

		logger.info("Output: " + json);

		return json;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApp.class, args);
	}
}