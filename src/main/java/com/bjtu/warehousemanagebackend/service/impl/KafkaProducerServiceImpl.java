package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(String topic, Event event) {
        kafkaTemplate.send(topic, event);
    }
}
