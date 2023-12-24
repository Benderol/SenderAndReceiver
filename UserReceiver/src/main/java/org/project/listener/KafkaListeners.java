package org.project.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.project.services.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListeners {

    private final UserService service;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "UserReceiver")
    public void getTask(ConsumerRecord<String, String> record){
        log.info(service.giveTaskToUser(record.key(), record.value()));
    }

    @KafkaListener(topics = "${spring.kafka.user-topic}", groupId = "UserReceiver")
    public void getNewUser(ConsumerRecord<String, String > record) {
        log.info(service.createUserFromJson(record.value()));
    }
}
