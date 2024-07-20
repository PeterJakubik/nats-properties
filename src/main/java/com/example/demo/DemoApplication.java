package com.example.demo;

import io.nats.client.Connection;
import io.nats.spring.boot.autoconfigure.NatsAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({NatsAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	Connection natsConnection;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(DemoApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		LOG.info("Checking configured NATS properties");

		var natsServer = natsConnection.getConnectedUrl();
		LOG.info("Connected to NATS server: {}",natsServer);

		var cleanup = natsConnection.getOptions().getRequestCleanupInterval();
		LOG.info("Request cleanup interval: {}",cleanup);
	}
}
