package com.example.projectgord8.repository;

import com.example.projectgord8.entity.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
    UserAction findUserActionsByDateActions(Date DateAction);
}
