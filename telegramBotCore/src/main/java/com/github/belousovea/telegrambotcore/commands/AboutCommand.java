package com.github.belousovea.telegrambotcore.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

@Component
public class AboutCommand extends MainMenuCommand {
    public AboutCommand() {
        super(3,new BotCommand("about", "About this bot"));
    }
}
