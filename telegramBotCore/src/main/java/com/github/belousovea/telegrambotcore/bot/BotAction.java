package com.github.belousovea.telegrambotcore.bot;

import com.github.belousovea.telegrambotcore.dialog.Dialog;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotAction {

    SendMessage replyMessage(Dialog dialog, Update update);

    String getName();

    boolean isApplicable(Dialog dialog, Update update);
}
