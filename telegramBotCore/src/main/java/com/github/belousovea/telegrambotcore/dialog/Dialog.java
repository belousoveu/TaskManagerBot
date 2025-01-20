package com.github.belousovea.telegrambotcore.dialog;

import com.github.belousovea.telegrambotcore.models.AbstractUser;
import lombok.Data;

@Data
public class Dialog {
    private State currentState;
    long chatId;
    AbstractUser user;


    public Dialog(long chatId, AbstractUser user) {
        this.chatId = chatId;
        this.user = user;
        if (user == null) {
            currentState = State.TIME_SETUP;
        } else {
            currentState = State.START;
        }
    }

    public enum State {
        START,
        TIME_SETUP,
        BASIC_STATE,
        ADD_NEW_MEMO,
        ADD_NEW_PERIODIC_MEMO,
        MEMO_LIST,
        CALENDAR

    }
}
