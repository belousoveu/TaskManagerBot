package com.github.belousovea.telegrambotcore.dialog;

import com.github.belousovea.telegrambotcore.bot.BotAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DialogManager {

    private final Map<Long, Dialog> dialogs = new HashMap<>();
    private final List<BotAction> actions;

    public DialogManager(List<BotAction> actions) {
        this.actions = actions;
    }

    public Dialog getDialog(Update update) {
        long chatId = getChatId(update);
        long userId = getUserId(update);

        return dialogs.computeIfAbsent(chatId, Dialog::new);
    }

    public SendMessage getAction(Update update) {
        if (update == null) {
            throw new NullPointerException("Update is null");
        }
        Dialog dialog = getDialog(update);
        log.info("Actions: {}", actions.size());
        BotAction defaultAction = actions.stream()
                .filter(action -> "unknown".equals(action.getName())).findFirst().orElseThrow();
        for (BotAction action : actions) {
            if (!action.getName().equals(defaultAction.getName()) && action.isApplicable(dialog, update)) {
                return action.replyMessage(dialog, update);
            }
        }

        return defaultAction.replyMessage(dialog, update);
    }

    private long getUserId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new IllegalArgumentException("No user id");
    }

    private long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new IllegalArgumentException("No chat id");
    }
}
