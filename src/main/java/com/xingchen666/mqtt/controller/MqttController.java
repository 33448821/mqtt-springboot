package com.xingchen666.mqtt.controller;

/**
 * Created by stars
 * Date 2024/10/17 13:35
 * Description
 */
import com.xingchen666.mqtt.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    private MqttService mqttService;

    @PostMapping("/publish")
    public String publish(@RequestParam String topic, @RequestParam String message) {
        try {
            mqttService.publish(topic, message);
            return "Message published";
        } catch (MqttException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/subscribe")
    public String subscribe(@RequestParam String topic) {
        try {
            mqttService.subscribe(topic);
            return "Subscribed to topic: " + topic;
        } catch (MqttException e) {
            return "Error: " + e.getMessage();
        }
    }
}

