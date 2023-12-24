package org.project.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.project.models.User;
import org.project.repositories.UserRepository;
import org.project.services.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    public String giveTaskToUser(String username, String task) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " was not found"));

        if(user.isHasNotCompletedTasks())
            throw new RuntimeException("User with username: " + username + " already has uncompleted task");

        setTaskAndSave(user, task, true);

        return "Task was successfully added";
    }

    @Override
    public String completeTask(int userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " was not found"));

        if(user.getTask() == null || !user.isHasNotCompletedTasks())
            throw new RuntimeException("User with id: " + userId + " does not have uncompleted tasks");

        setTaskAndSave(user, null, false);

        return "Task was completed";
    }

    @Override
    public String createUserFromJson(String value) {
        User user;
        try {
            user = objectMapper.readValue(value, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can not parse json object into user");
        }

        setTaskAndSave(user, null, false);

        return "User was created and saved";
    }

    private void setTaskAndSave(User user, String task, boolean hasNotCompletedTasks){
        user.setTask(task);
        user.setHasNotCompletedTasks(hasNotCompletedTasks);
        userRepository.save(user);
    }
}
