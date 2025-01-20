package com.github.belousovea.telegrambotcore.bot.actions;

import com.github.belousovea.telegrambotcore.bot.BotAction;
import com.github.belousovea.telegrambotcore.dialog.Dialog;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class AboutAction implements BotAction {

    @Override
    public SendMessage replyMessage(Dialog dialog, Update update) {
        return SendMessage.builder()
                .chatId(dialog.getChatId())
                .text("I'm a bot created by @belousovea")
                .build();
    }

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public boolean isApplicable(Dialog dialog, Update update) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().toLowerCase().startsWith("/about");
    }
}
