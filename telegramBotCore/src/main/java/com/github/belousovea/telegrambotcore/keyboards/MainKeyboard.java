package com.github.belousovea.telegrambotcore.keyboards;

import com.github.belousovea.telegrambotcore.keyboards.buttons.MainKeyboardButton;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
@Getter
public class MainKeyboard {

    private final List<MainKeyboardButton> buttons;
    private final ReplyKeyboardMarkup mainKeyboard;

    public MainKeyboard(List<MainKeyboardButton> buttons) {
        buttons.sort(MainKeyboardButton::compareTo);
        this.buttons = buttons;
        List<KeyboardRow> keyboardRows = buttons
                .stream()
                .map(b -> new KeyboardRow(b.getKeyboardButton()))
                .toList();
        mainKeyboard = new ReplyKeyboardMarkup(keyboardRows);
    }

}
