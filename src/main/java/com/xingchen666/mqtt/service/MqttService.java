package com.xingchen666.mqtt.service;

/**
 * Created by stars
 * Date 2024/10/17 13:10
 * Description
 */
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    @Autowired
    private MqttClient mqttClient;

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttClient.publish(topic, mqttMessage);
    }

    public void subscribe(String topic) throws MqttException {
        mqttClient.subscribe(topic, (topic1, mqttMessage) -> {
            String payload = new String(mqttMessage.getPayload());
            System.out.println("Received message: " + payload);
        });
    }
}
