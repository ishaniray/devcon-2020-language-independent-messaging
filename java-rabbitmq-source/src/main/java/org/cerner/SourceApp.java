package org.cerner;

import java.util.List;

import org.cerner.dao.SourceAppDao;
import org.cerner.dto.CovidPt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
@SpringBootApplication
public class SourceApp {

	private static Logger logger = LoggerFactory.getLogger(SourceApp.class);

	@Autowired
	private SourceAppDao dao;

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<List<CovidPt>> timeMessageSource() {
		logger.info("Sending list of Covid patients...");
		return () -> MessageBuilder.withPayload(dao.getAllCovidPatients()).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceApp.class, args);
	}
}
