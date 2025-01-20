package com.github.belousovea.telegrambotcore.services;

import com.github.belousovea.telegrambotcore.repositories.MemoRepository;
import org.springframework.stereotype.Component;

@Component
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }
}
