package forest.detector.bot;

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
    String chatID = "";

    // bot answer logic
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chatID = update.getMessage().getChatId().toString();
            System.out.println(chatID);
            Message message = update.getMessage();
            if(message != null && message.hasText()) {
                String text = message.getText();
                System.out.println(text);
                    if (text.equalsIgnoreCase("/key") || text.equalsIgnoreCase("бот")) { // need fix group private settings
                        try {
                            execute(sendInlineKeyBoardMessage(chatID));
                            System.out.println("keyboard end");
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else if ("/help".equals(text)) {
                    ScanChatID(message);
                    answerMsg(message, "What can i help?");
                    } else if ("/test".equals(text)) {
                    groupAlert("Warning!!!");
                    }
                    else if ("/bot".equals(text)) {
                        answerMsg(message, "All my command:\n1.\"/help\" - in process\n2.\"/test\" - in process\n3.\"bot\" or \"бот\" - keyboard\n4.\"/bot\" - Show all commands\n");
                    }
            }
        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    // Scan Chat data
    public void ScanChatID(Message message){
        System.out.println("getChatData - " + message.getChat());
        System.out.println("getChatID - " + message.getChatId());
    }


    // Send msg to Forester group
    public void groupAlert(String text){
        sendMsg("-1001342864735", "New Info:\n" + text);
    }

    // send message to chat ID
    public void sendMsg(String id, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException exception){
            exception.printStackTrace();
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
            exception.printStackTrace();
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
        return "IT-Cluster-Java9-project";
    }

    public String getBotToken() {
        return "1262037289:AAHOtiJ2cHchTai7ZITRWQQDN0qY6PxVj6g";
    }
}
