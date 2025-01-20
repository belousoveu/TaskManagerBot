package com.github.belousovea.telegrambotcore.dialog;

import com.github.belousovea.telegrambotcore.bot.BotAction;
import com.github.belousovea.telegrambotcore.models.AbstractUser;
import com.github.belousovea.telegrambotcore.services.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DialogManager {

    private final Map<Long, Dialog> dialogs = new HashMap<>();
    private final UserService userService;
    private final List<BotAction> actions;

    public DialogManager(UserService userService, List<BotAction> actions) {
        this.userService = userService;
        this.actions = actions;
    }

    public Dialog getDialog(Update update) {
        long chatId = getChatId(update);
        long userId = getUserId(update);
        AbstractUser user = userService.getUser(userId);

        return dialogs.computeIfAbsent(chatId, id -> new Dialog(id, user));
    }

    public SendMessage getAction(Update update) {
        if (update == null) {
            throw new NullPointerException("Update is null");
        }
        Dialog dialog = getDialog(update);
        BotAction defaultAction = actions.stream()
                .filter(action -> "unknown".equals(action.getName())).findFirst().orElseThrow();
        for (BotAction action : actions) {
            if(!action.getName().equals(defaultAction.getName()) && action.isApplicable(dialog,update)) {
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
