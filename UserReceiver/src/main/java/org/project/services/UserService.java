package org.project.services;

public interface UserService {
    String giveTaskToUser(String username, String task);

    String completeTask(int userId);

    String createUserFromJson(String value);
}
