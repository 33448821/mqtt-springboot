package com.xingchen666.mqtt.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by stars
 * Date 2024/10/17 13:10
 * Description
 */
@Configuration
public class MqttConfig {

    private static final String BROKER_URL = "tcp://localhost:1883"; // 修改为您的 MQTT 代理地址
    private static final String CLIENT_ID = MqttClient.generateClientId();
    private static final Logger logger = LogManager.getLogger(MqttConfig.class);

    @Bean
    public MqttClient mqttClient() throws Exception {
        MqttClient mqttClient = new MqttClient(BROKER_URL, CLIENT_ID);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        mqttClient.connect(options);

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                logger.error("Connection lost!", cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                // 处理接收到的消息
                String payload = new String(message.getPayload());
                logger.info("Received message on topic '{}': {}", topic, payload);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // 发送完成后的处理（如果需要）
            }
        });

        //C:\Program Files\mosquitto>mosquitto_sub -h 121.4.122.181 -t "home/garden/succulent/data"
        mqttClient.subscribe("home/garden/succulent/data"); // 修改为您的主题

        return mqttClient;
    }
}



