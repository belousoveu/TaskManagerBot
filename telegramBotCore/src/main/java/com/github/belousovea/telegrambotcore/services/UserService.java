package com.github.belousovea.telegrambotcore.services;

import com.github.belousovea.telegrambotcore.models.AbstractUser;
import com.github.belousovea.telegrambotcore.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AbstractUser getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

}
