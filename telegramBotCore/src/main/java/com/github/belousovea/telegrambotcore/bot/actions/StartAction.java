package com.github.belousovea.telegrambotcore.bot.actions;

import com.github.belousovea.telegrambotcore.bot.BotAction;
import com.github.belousovea.telegrambotcore.dialog.Dialog;
import com.github.belousovea.telegrambotcore.services.UserService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Data
public class StartAction implements BotAction {
    private String name= "start";
    private final UserService userService;

    @Override
    public SendMessage replyMessage(Dialog dialog, Update update) {


    return SendMessage.builder()
            .chatId(dialog.getChatId())
            .text(String.format("Привет, %s! бот, который поможет вам управлять бизнесом", dialog.getUser().getUsername()))
            .build();
    }

    @Override
    public boolean isApplicable(Dialog dialog, Update update) {
        return false;
    }
}
