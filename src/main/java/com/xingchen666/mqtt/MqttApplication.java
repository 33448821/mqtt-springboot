package com.xingchen666.mqtt;

import com.xingchen666.mqtt.service.MqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqttApplication {
	private static final Logger logger = LoggerFactory.getLogger(MqttService.class);

	public static void main(String[] args) {
		SpringApplication.run(MqttApplication.class, args);
	}

}
