package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.domain.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl {

    @KafkaListener(topics = "logistics", groupId = "group_id")
    public void handleLogisticsEvent(Event event) {
        // Handle logistics event
    }

    @KafkaListener(topics = "inspection", groupId = "group_id")
    public void handleInspectionEvent(Event event) {
        // Handle inspection event
    }

    @KafkaListener(topics = "storage", groupId = "group_id")
    public void handleStorageEvent(Event event) {
        // Handle storage event
    }
}
