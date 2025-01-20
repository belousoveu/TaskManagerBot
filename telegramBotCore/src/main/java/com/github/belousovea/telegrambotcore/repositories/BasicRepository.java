package com.github.belousovea.telegrambotcore.repositories;

import com.github.belousovea.telegrambotcore.models.AbstractUser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasicRepository implements UserRepository, MemoRepository{
    @Override
    public Optional<AbstractUser> findById(long id) {
        return Optional.empty();
    }
}
