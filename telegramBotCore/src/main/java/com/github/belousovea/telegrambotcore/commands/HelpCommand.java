package com.github.belousovea.telegrambotcore.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

@Component
public class HelpCommand extends MainMenuCommand {
    public HelpCommand() {
        super(2, new BotCommand("help", "Помощь по работе с ботом"));
    }
}
