package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.models.KafkaMessageSender;
import org.project.models.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    @Value(value = "${spring.kafka.user-topic}")
    private String userTopic;

    private final KafkaTemplate<String, String> template;

    private final KafkaTemplate<String, UserInfo> templateWithUser;

    @PostMapping("/publish")
    public ResponseEntity<String> publish( @RequestBody KafkaMessageSender sender ){
        template.send(topic,sender.getUsername(), sender.getTask());

        return ResponseEntity.ok("Data was send");
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser( @RequestBody UserInfo userInfo ){
        templateWithUser.send(userTopic,userInfo);

        return ResponseEntity.ok("Information about new user was send");
    }
}
