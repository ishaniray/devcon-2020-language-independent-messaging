package org.cerner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@EnableBinding(Sink.class)
@SpringBootApplication
public class SinkApp {

	private static Logger logger = LoggerFactory.getLogger(SinkApp.class);

	private static final String REST_ENDPOINT = "http://629672d9246a.ngrok.io/testJsonPost";

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@StreamListener(Sink.INPUT)
	public void loggerSink(String json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> payload = new HttpEntity<>(json, headers);

		restTemplate.exchange(REST_ENDPOINT, HttpMethod.POST, payload, Void.class);

		logger.info("Received: " + json);
	}

	public static void main(String[] args) {
		SpringApplication.run(SinkApp.class, args);
	}
}
