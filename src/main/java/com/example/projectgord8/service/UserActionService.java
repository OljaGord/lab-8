package com.example.projectgord8.service;

import com.example.projectgord8.entity.User;
import com.example.projectgord8.entity.UserAction;
import com.example.projectgord8.repository.UserActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("UserActionService")
public class UserActionService {

    private UserActionRepository userActionRepository;

    @Autowired
    public UserActionService(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    public void saveAction(String username, String action) {
        UserAction userAction = new UserAction();
        userAction.setDateActions(new Date());
        String description = "user " + username + " setAction " + action;
        userAction.setDescription(description);

        userActionRepository.save(userAction);
    }
}
