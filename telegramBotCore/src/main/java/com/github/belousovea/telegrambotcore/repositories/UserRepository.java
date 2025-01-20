package com.github.belousovea.telegrambotcore.repositories;

import com.github.belousovea.telegrambotcore.models.AbstractUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<AbstractUser> findById(long id);
}

