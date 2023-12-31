package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/complete-task/{userId}")
    public ResponseEntity<String> completeTask(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.completeTask(userId));
    }


}
