package forest.detector.bot;

import forest.detector.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    private PropertyBot property = new PropertyBot();

    private static Logger log = LoggerFactory.getLogger(Launcher.class);

    // bot answer logic
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();

                if (message != null && message.hasText()) {
                    switch (message.getText()) {
                        case "/bot":
                            answerMsg(message, "All my command:\n1. \"/bot\" - Show all commands\n2.\"/key\" - Show keyboard");
                            break;
                        case "/key":
                            execute(sendInlineKeyBoardMessage(update.getMessage().getChatId().toString()));
                            break;
                    }
                }
            } else if (update.hasCallbackQuery()) { // keyboard answer
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            }
        } catch (TelegramApiException exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    // Send msg to Forester channel
    public void groupAlert(String text){
        sendMsg(property.getForestChannelID(), text);
    }

    // send message to chat ID
    public void sendMsg(String id, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException exception){
            log.error(exception.getMessage(), exception);
        }
    }
    // answer to msg for bot in chat
    private void answerMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException exception){
            log.error(exception.getMessage(), exception);
        }
    }
    // Inline clickable keyboard
    public static SendMessage sendInlineKeyBoardMessage(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Палять луг!").setCallbackData("Викликано пожежну охорону"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Крадуть ліс!").setCallbackData("Викликано Наряд!"));

        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("Посадив дерево").setCallbackData("Дякуємо за турботу про довкілля!"));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Що сталось?").setReplyMarkup(inlineKeyboardMarkup);
    }

    public String getBotUsername() {
        return property.getBotName();
    }

    public String getBotToken() {
        return property.getToken();
    }
}
